package com.location.locationmanager.Dto.LocationDto;

import com.location.locationmanager.JpaClass.LocationTable.Location;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
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
