package com.example.location_server.Dto.LocationDto;

import com.example.location_server.JpaClass.LocationTable.Location;
import lombok.Data;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecommendResultDto {
    private int locationId;
    private String category;
    private double latitude;
    private double longitude;
    private int score;
    private int visitCount;
    private String title;
    private String description;
    private List<ImageBase64Dto> images;

    static public RecommendResultDto toRecommendResultDto(Location location, List<byte[]> imageBytes) {
        RecommendResultDto dto = new RecommendResultDto();

        dto.setLocationId(location.getLocationId());
        dto.setCategory(location.getCategory());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        dto.setScore(location.getScore());
        dto.setVisitCount(location.getVisitCount());
        dto.setTitle(location.getTitle());
        dto.setDescription(location.getDescription());

        List<ImageBase64Dto> images = imageBytes.stream().map(bytes -> {
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            ImageBase64Dto imageDto = new ImageBase64Dto();

            imageDto.setImage(base64Image);

            return imageDto;
        }).collect(Collectors.toList());
        dto.setImages(images);

        return dto;
    }
}
