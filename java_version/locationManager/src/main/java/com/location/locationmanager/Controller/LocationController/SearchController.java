package com.location.locationmanager.Controller.LocationController;

import com.location.locationmanager.Dto.LocationDto.InformationDto;
import com.location.locationmanager.Dto.LocationDto.LocationDto;
import com.location.locationmanager.Dto.LocationDto.SearchDto;
import com.location.locationmanager.Dto.LocationDto.SearchInformationDto;
import com.location.locationmanager.Service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/location/search")
@RequiredArgsConstructor
@Slf4j
@RestController
public class SearchController {
    private final SearchService service;

    /* range 범위에 따른 모든 장소 검색 */
    @GetMapping("/in-range")
    public ResponseEntity searchInRangeLocation(@ModelAttribute SearchDto dto) {
        // 범위 검색 결과
        List<LocationDto> response = service.findInRange(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("in-range에 문제가 발생했습니다.");
        }
    }

    /* Max Range 안에서 Min Range를 제외한 범위 검색 */
    @GetMapping("/within-range")
    public ResponseEntity searchWithinRangeLocation(@ModelAttribute SearchDto dto) {
        // 범위 검색 결과
        List<LocationDto> response = service.findWithinRange(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("within-range에 문제가 발생했습니다.");
        }
    }

    /* 장소의 Information 조회 (title, description, images) */
    @GetMapping("/information")
    public ResponseEntity searchLocationInformation(@ModelAttribute SearchInformationDto dto) {
        // 장소의 Information 조회 결과
        InformationDto response = service.findLocationInformation(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("찾을 수 없는 장소입니다.");
        }
    }

    /* latitude, longitude, category를 기반으로 locationId 반환 */
    @GetMapping("/get-location-id")
    public ResponseEntity searchLocationId(@ModelAttribute SearchInformationDto dto) {
        // locationId 검색 결과
        LocationDto response = service.findLocationId(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("찾을 수 없는 장소입니다.");
        }
    }

    /* category, 현재 위치, ragne를 기반으로 장소 추천 */
    @GetMapping("/recommend-location")
    public ResponseEntity recommendLocation(@ModelAttribute SearchDto dto) {
        // 추천 결과
        LocationDto response = service.recommendLocation(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("추천할 장소가 없습니다.");
        }
    }
}
