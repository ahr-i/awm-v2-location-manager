package com.example.location_server.Dto.LocationDto;

import com.example.location_server.JpaClass.LocationTable.Location;
import lombok.Data;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuickRecommendResultDto {
    private int locationId;
    private String category;
    private double latitude;
    private double longitude;
    private String title;
    private byte[] image;

    static public QuickRecommendResultDto toQuickRecommendResultDto(Location location, byte[] imageBytes) {
        QuickRecommendResultDto dto = new QuickRecommendResultDto();

        dto.setLocationId(location.getLocationId());
        dto.setCategory(location.getCategory());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        dto.setTitle(location.getTitle());
        dto.setImage(imageBytes);

        return dto;
    }
}
