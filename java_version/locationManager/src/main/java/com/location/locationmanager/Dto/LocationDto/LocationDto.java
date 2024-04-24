package com.location.locationmanager.Dto.LocationDto;

import com.location.locationmanager.JpaClass.LocationTable.Location;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
public class LocationDto {
    private int locationId;
    private double latitude;
    private double longitude;
    private String category;
    private int visitCount;

    public static List<LocationDto> locationToLocationDto(List<Location> locations) {
        return locations.stream().map(location -> {
            LocationDto dto = new LocationDto();

            dto.setLocationId(location.getLocationId());
            dto.setLatitude(location.getLatitude());
            dto.setLongitude(location.getLongitude());
            dto.setCategory(location.getCategory());
            dto.setVisitCount(location.getVisitCount());

            return dto;
        }).collect(Collectors.toList());
    }
}
