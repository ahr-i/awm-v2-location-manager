package com.example.location_server.Controller.LocationController;

import com.example.location_server.Communicator.Authentication.AuthenticationCommunicator;
import com.example.location_server.Dto.LocationDto.*;
import com.example.location_server.Service.LocationService.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/location")
@RequiredArgsConstructor
@Slf4j
@RestController
public class SearchController {
    private final SearchService service;
    private final AuthenticationCommunicator authentication;

    /* range 범위에 따른 모든 장소 검색 */
    @GetMapping("/search/in-range")
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
    @GetMapping("/search/within-range")
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
    @GetMapping("/search/information")
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
    @GetMapping("/search/get-location-id")
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
    @GetMapping("/search/recommend-location")
    public ResponseEntity recommendLocation(@ModelAttribute SearchDto dto) {
        // 추천 결과
        LocationDto response = service.recommendLocation(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("추천할 장소가 없습니다.");
        }
    }

    // Image Processing Server를 사용하여 이미지 기반 장소 추천
    @PostMapping("/search/recommend")
    public ResponseEntity recommendLocationWithImageProcessing(@ModelAttribute RecommendDto dto) {
        List<RecommendResultDto> response = service.recommendLocationWithImageProcessing(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("추천할 장소가 없습니다.");
        }
    }

    // Image Processing Server를 사용하여 이미지 기반 장소 추천 (빠른 버전, 해당 장소의 이미지를 활용함.)
    @PostMapping("/search/recommend-quick")
    public ResponseEntity quickRecommendLocationWithImageProcessing(@RequestBody QuickRecommendDto dto) {
        List<QuickRecommendResultDto> response = service.quickRecommendLocationWithImageProcessing(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("추천할 장소가 없습니다.");
        }
    }

    @PostMapping("/chattingbot")
    public ResponseEntity chattingBot(@RequestHeader("Authorization") String jwt, @RequestBody ChattingBotQueryDto dto) {
        String userId = authentication.authentication(jwt);
        if(Objects.equals(userId, "")) {
            return ResponseEntity.badRequest().body("Unauthorized JWT.");
        }

        ChattingBotResponseDto response = service.chattingBot(dto);
        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("ChatBot Error.");
        }
    }
}
