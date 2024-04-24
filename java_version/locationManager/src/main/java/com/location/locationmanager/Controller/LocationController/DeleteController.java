package com.location.locationmanager.Controller.LocationController;

import com.location.locationmanager.Dto.LocationDto.LocationDto;
import com.location.locationmanager.Service.DeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/location")
@RequiredArgsConstructor
@Slf4j
@RestController
public class DeleteController {
    private final DeleteService service;

    /* 장소 삭제 */
    @PostMapping("/delete")
    public ResponseEntity deleteLocation(@RequestBody LocationDto dto){
        // 장소 삭제의 결과
        Boolean result = service.delete(dto);

        if(result) {
            return ResponseEntity.ok().body("장소 삭제에 성공했습니다.");
        } else {
            return ResponseEntity.badRequest().body("장소 삭제에 실패했습니다.");
        }
    }
}
