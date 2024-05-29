package com.example.location_server.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class pingController {
    @GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok().body(null);
    }
}