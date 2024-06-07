package com.example.location_server.Communicator.ImageProcessing;

import com.example.location_server.Communicator.CommSetting;
import com.example.location_server.Dto.LocationDto.LocationDto;
import com.example.location_server.Dto.LocationDto.RecommendDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageProcessingComm {
    private final CommSetting setting;

    public boolean inspection(int locationId, String locationImage, String category) {
        String url = setting.getImageProcessingAddress() + "/inspection/";

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Create HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("location_id", String.valueOf(locationId));
        requestBody.put("image_base64", locationImage);
        requestBody.put("category", category);

        // Convert request body to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = null;
        try {
            jsonRequestBody = objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Failed to convert request body to JSON.");
            return false;
        }

        // Create HttpEntity with headers and body
        HttpEntity<String> entity = new HttpEntity<>(jsonRequestBody, headers);
        try {
            // Send POST request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Get response body
            String responseBody = response.getBody();

            // Parse response JSON and extract is_same_place
            JsonNode root = objectMapper.readTree(responseBody);
            boolean isSamePlace = root.path("is_same_place").asBoolean();
            System.out.println("Is Same Place: " + isSamePlace);

            return isSamePlace;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Currently, the Image Processing Server is not functioning.");

            return true;
        }
    }

    public List<LocationDto> recommend(RecommendDto dto) {
        String url = setting.getImageProcessingAddress() + "/recommend/";

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Create HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("image_base64", dto.getImage());
        requestBody.put("candidate", String.valueOf(dto.getCandidate()));
        requestBody.put("category", dto.getCategory());

        // Convert request body to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = null;
        try {
            jsonRequestBody = objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Failed to convert request body to JSON.");

            return null;
        }

        // Create HttpEntity with headers and body
        HttpEntity<String> entity = new HttpEntity<>(jsonRequestBody, headers);
        try {
            // Send POST request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Get response body
            String responseBody = response.getBody();

            // Log the response body for debugging
            log.info("Response Body: {}", responseBody);

            // Parse response JSON and extract location_id
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode locationIdsNode = root.path("location_id");
            if (locationIdsNode.isArray()) {
                List<LocationDto> results = new ArrayList<>();
                for (JsonNode node : locationIdsNode) {
                    LocationDto result = new LocationDto();
                    result.setLocationId(Integer.parseInt(node.asText()));
                    results.add(result);
                }
                return results;
            } else {
                log.warn("Unexpected response format.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Currently, the Image Processing Server is not functioning.");
            return null;
        }
    }
}
