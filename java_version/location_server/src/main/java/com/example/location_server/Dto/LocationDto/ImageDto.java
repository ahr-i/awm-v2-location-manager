package com.example.location_server.Dto.LocationDto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImageDto {
    private byte[] image;

    public static List<ImageDto> locationImageToDto(List<byte[]> locationImages) {
        return locationImages.stream().map(image -> {
            ImageDto dto = new ImageDto();

            dto.setImage(image);

            return dto;
        }).collect(Collectors.toList());
    }
}
