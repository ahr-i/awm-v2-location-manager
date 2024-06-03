package com.example.location_server.Communicator.Authentication;

import com.example.location_server.Communicator.CommSetting;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationCommunicator {
    private final CommSetting setting;

    public String authentication(String jwt) {
        String url = setting.getAuthenticationAddress() + "/user/authentication/get-id";

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Create HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", jwt);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Get response body
        String responseBody = response.getBody();

        // Parse response JSON and extract userId
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            String userId = root.path("userId").asText();
            // Log userId
            System.out.println("UserId: " + userId);
            log.info("id:{}", userId);

            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
