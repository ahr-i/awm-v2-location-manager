package com.example.location_server.Dto.LocationDto;

import com.example.location_server.JpaClass.LocationTable.Location;
import lombok.Data;

import java.util.List;

@Data
public class InformationDto {
    private String title;
    private String description;
    private List<ImageDto> images;

    static public InformationDto locationToInformationDto(Location location, List<ImageDto> images) {
        InformationDto informationDto = new InformationDto();

        informationDto.setTitle(location.getTitle());
        informationDto.setDescription(location.getDescription());
        informationDto.setImages(images);

        return informationDto;
    }
}
