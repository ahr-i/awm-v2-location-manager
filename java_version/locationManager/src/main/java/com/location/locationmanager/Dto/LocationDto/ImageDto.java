package com.location.locationmanager.Dto.LocationDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
