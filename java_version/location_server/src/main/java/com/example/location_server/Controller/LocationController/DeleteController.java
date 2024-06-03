package com.example.location_server.Controller.LocationController;

import com.example.location_server.Communicator.Authentication.AuthenticationCommunicator;
import com.example.location_server.Dto.LocationDto.LocationDto;
import com.example.location_server.Service.LocationService.DeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/user/location")
@RequiredArgsConstructor
@Slf4j
@RestController
public class DeleteController {
    private final DeleteService service;
    private final AuthenticationCommunicator authentication;

    /* 장소 삭제 */
    @PostMapping("/delete")
    public ResponseEntity deleteLocation(@RequestHeader("Authorization") String jwt, @RequestBody LocationDto dto){
        String userId = authentication.authentication(jwt);
        if(Objects.equals(userId, "")) {
            return ResponseEntity.badRequest().body("Unauthorized JWT.");
        }

        // 장소 삭제의 결과
        Boolean result = service.delete(dto);

        if(result) {
            return ResponseEntity.ok().body("Successfully deleted the location.");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete the location.");
        }
    }
}
