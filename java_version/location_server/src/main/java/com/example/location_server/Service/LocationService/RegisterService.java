package com.example.location_server.Service.LocationService;

import com.example.location_server.Communicator.ImageProcessing.ImageProcessingComm;
import com.example.location_server.Dto.LocationDto.LocationDto;
import com.example.location_server.Dto.LocationDto.RegisterDto;
import com.example.location_server.JpaClass.LocationTable.Contributor;
import com.example.location_server.JpaClass.LocationTable.Location;
import com.example.location_server.JpaClass.LocationTable.LocationImage;
import com.example.location_server.Repository.JpaRepository.UserRepository;
import com.example.location_server.Repository.LocationRepository.ContributorRepository;
import com.example.location_server.Repository.LocationRepository.LocationImageRepository;
import com.example.location_server.Repository.LocationRepository.LocationRepository;
import com.example.location_server.Setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final LocationRepository locationRepository;
    private final ContributorRepository contributorRepository;
    private final LocationImageRepository locationImageRepository;
    private final UserRepository userRepository;
    private final LocationSetting setting;
    private final ImageProcessingComm imageProcessing;

    /* 장소 등록 */
    public boolean register(RegisterDto dto, String userId) {
        try {
            dto.setUserId(userId);

            Location location = RegisterDto.toLocation(dto);
            Contributor contributor = RegisterDto.toContributor(dto);
            int existingLocationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());
            int locationId = 0;

            // 기존의 장소가 있는 경우
            if(existingLocationId != -1) {
                // locationId를 기존 장소의 ID로 교체
                locationId = existingLocationId;
                location.setLocationId(locationId);
                // 기존 장소 검색
                Optional<Location> existingLocation = locationRepository.findById(locationId);

                // 기존 장소가 있을 경우 UPDATE 진행
                if (existingLocation.isPresent()) {
                    Location updatedLocation = existingLocation.get();

                    // Title, Description 최신으로 변경
                    updatedLocation.setTitle(dto.getTitle());
                    updatedLocation.setDescription(dto.getDescription());
                }
            } else {
                // 기존 장소가 없을 경우 새로운 LocationId를 생성 및 받아옴
                locationId = locationRepository.save(location).getLocationId();
            }

            // Dto에 이미지가 존재하는 경우
            if(dto.getImage() != null) {
                // 이미지 검증
                boolean isSamePlace = imageProcessing.inspection(locationId, dto.getImage(), dto.getCategory());
                if(!isSamePlace) {
                    log.info("Stopping place registration. (Place verification result: mismatch)");

                    return false;
                }

                LocationImage locationImage = RegisterDto.toLocationImage(dto);
                locationImage.setLocationId(locationId);

                // 이미지 등록
                locationImageRepository.save(locationImage);
            }
            // Contributor 등록
            contributor.setLocationId(locationId);
            contributor.setRate(setting.getUserRate());

            // DB에 저장
            locationRepository.updateScore(locationId, setting.getRegisterBaseScore());
            contributorRepository.save(contributor);
            userRepository.updateUserRank(userId);

            // 성공적으로 등록
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    /* latutude, longitude, category를 기반으로 장소 검색 */
    public int getLocationId(double latitude, double longitude, String category) {
        // latitude, ongitude, category가 일치하는 장소 검색
        List<Location> result = locationRepository.findLocationByCategoryInRange(latitude, longitude, setting.getLatitudeRange(), setting.calculateLongitudeRange(latitude), category);

        if(!result.isEmpty()) {
            // 일치하는 장소가 있는 경우
            return result.get(0).getLocationId();
        } else {
            // 일치하는 장소가 없는 경우
            return -1;
        }
    }

    /* 장소 추천 */
    public boolean agree(LocationDto dto) {
        try {
            // 기존의 장소 Id
            int existingLocationId = dto.getLocationId();

            // 기존의 장소가 있는 경우
            if(existingLocationId != 0) {
                locationRepository.updateScore(existingLocationId, setting.getRegisterBaseScore());
            }

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    /* 장소 정보 수정 및 추가 */
    public boolean edit(RegisterDto dto, String userId) {
        try {
            dto.setUserId(userId);

            Contributor contributor = RegisterDto.toContributor(dto);
            int existingLocationId = dto.getLocationId();

            // 기존의 장소가 있는 경우
            if(existingLocationId != 0) {
                // 기존 장소 검색
                Optional<Location> existingLocation = locationRepository.findById(existingLocationId);

                // 기존 장소가 있을 경우 UPDATE 진행
                if (existingLocation.isPresent()) {
                    Location updatedLocation = existingLocation.get();

                    // Titl, Description 최신으로 변경
                    updatedLocation.setTitle(dto.getTitle());
                    updatedLocation.setDescription(dto.getDescription());

                    locationRepository.save(updatedLocation);
                }
            }

            // Dto에 이미지가 존재하는 경우
            if(dto.getImage() != null) {
                LocationImage locationImage = RegisterDto.toLocationImage(dto);

                locationImage.setLocationId(existingLocationId);

                // 이미지 등록
                locationImageRepository.save(locationImage);
            }
            // Contributor 등록
            contributor.setLocationId(existingLocationId);
            contributor.setRate(setting.getUserRate());

            // DB에 저장
            locationRepository.updateScore(existingLocationId, setting.getRegisterBaseScore());
            contributorRepository.save(contributor);
            userRepository.updateUserRank(userId);

            // 성공적으로 등록
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }
}
