package com.example.location_server.Controller.LocationController;

import com.example.location_server.Communicator.Authentication.AuthenticationCommunicator;
import com.example.location_server.Dto.LocationDto.LocationDto;
import com.example.location_server.Dto.LocationDto.RegisterDto;
import com.example.location_server.Service.LocationService.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/user/location")
@RequiredArgsConstructor
@Slf4j
@RestController
public class RegisterController {
    private final RegisterService service;
    private final AuthenticationCommunicator authentication;

    /* 장소 등록 */
    @PostMapping("/register")
    public ResponseEntity registerLocation(@RequestHeader("Authorization") String jwt,  @RequestBody RegisterDto dto){
        String userId = authentication.authentication(jwt);
        if(Objects.equals(userId, "")) {
            return ResponseEntity.badRequest().body("Unauthorized JWT.");
        }

        // 장소 등록의 결과
        Boolean result = service.register(dto, userId);

        if(result) {
            return ResponseEntity.ok().body("장소 등록에 성공했습니다.");
        } else {
            return ResponseEntity.badRequest().body("장소 등록에 실패했습니다.");
        }
    }

    /* 장소 추천 */
    @PostMapping("/agree")
    public ResponseEntity agreeLocation(@RequestHeader("Authorization") String jwt, @RequestBody LocationDto dto){
        String userId = authentication.authentication(jwt);
        if(Objects.equals(userId, "")) {
            return ResponseEntity.badRequest().body("Unauthorized JWT.");
        }

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
    public ResponseEntity editLocation(@RequestHeader("Authorization") String jwt, @RequestBody RegisterDto dto){
        String userId = authentication.authentication(jwt);
        if(Objects.equals(userId, "")) {
            return ResponseEntity.badRequest().body("Unauthorized JWT.");
        }

        // 수정 및 추가 결과
        Boolean result = service.edit(dto, userId);

        if(result) {
            return ResponseEntity.ok().body("장소 수정에 성공했습니다.");
        } else {
            return ResponseEntity.badRequest().body("장소 수정에 실패했습니다.");
        }
    }
}
