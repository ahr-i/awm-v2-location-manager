package com.location.locationmanager.Dto.LocationDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SearchInformationDto {
    private double latitude;
    private double longitude;
    private String category;
}
