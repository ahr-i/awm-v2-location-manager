package com.location.locationmanager.Controller.LocationController;

import com.location.locationmanager.Dto.LocationDto.LocationDto;
import com.location.locationmanager.Dto.LocationDto.RegisterDto;
import com.location.locationmanager.Service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/location")
@RequiredArgsConstructor
@Slf4j
@RestController
public class RegisterController {
    private final RegisterService service;

    /* 장소 등록 */
    @PostMapping("/register")
    public ResponseEntity registerLocation(@RequestBody RegisterDto dto, Authentication authentication){
        // JWT 파싱
        //PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        // 장소 등록의 결과
        Boolean result = service.register(dto, dto.getUserId());

        if(result) {
            return ResponseEntity.ok().body("장소 등록에 성공했습니다.");
        } else {
            return ResponseEntity.badRequest().body("장소 등록에 실패했습니다.");
        }
    }

    /* 장소 추천 */
    @PostMapping("/recommend")
    public ResponseEntity agreeLocation(@RequestBody LocationDto dto){
        // 추천 결과
        Boolean result = service.agree(dto);

        if(result) {
            return ResponseEntity.ok().body("장소 추천에 성공했습니다.");
        } else {
            return ResponseEntity.badRequest().body("장소 추천에 실패했습니다.");
        }
    }

    /* 장소 title, image 수정 및 추가 */
    @PostMapping("/edit")
    public ResponseEntity editLocation(@RequestBody RegisterDto dto, Authentication authentication){
        // JWT 파싱
        //PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        // 수정 및 추가 결과
        Boolean result = service.edit(dto, dto.getUserId());

        if(result) {
            return ResponseEntity.ok().body("장소 수정에 성공했습니다.");
        } else {
            return ResponseEntity.badRequest().body("장소 수정에 실패했습니다.");
        }
    }
}
