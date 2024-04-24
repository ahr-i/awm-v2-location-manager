package com.location.locationmanager.Service;

import com.location.locationmanager.Dto.LocationDto.LocationDto;
import com.location.locationmanager.JpaClass.LocationTable.Location;
import com.location.locationmanager.Repository.LocationRepository.LocationRepository;
import com.location.locationmanager.setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteService {
    private final LocationRepository locationRepository;
    private final LocationSetting setting;

    /* 기존 장소 삭제 */
    public boolean delete(LocationDto dto) {
        try {
            // latitude, longitude, category를 통해 기존 장소가 있는지 검색
            int existingLocationId = dto.getLocationId();

            // 기존 장소가 있는 경우
            // 점수를 차감시킴
            if(existingLocationId != 0) {
                locationRepository.updateScore(existingLocationId, setting.getDeleteBaseScore());
                //log.info("locationId: {}", existingLocationId);

                return true;
            }

            // 기존 장소가 없는 경우
            return false;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    /* latitude, longitude, category를 기반으로 기존의 장소 ID 검색 */
    public int getLocationId(double latitude, double longitude, String category) {
        // latitude, longitude, category로 장소를 찾음
        List<Location> result = locationRepository.findLocationByCategory(latitude, longitude, category);

        if(!result.isEmpty()) {
            // 기존 장소가 있는 경우
            return result.get(0).getLocationId();
        } else {
            // 검색된 장소가 없는 경우
            return -1;
        }
    }
}
