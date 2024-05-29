package com.example.location_server.Dto.LocationDto;

import lombok.Data;

@Data
public class SearchInformationDto {
    private double latitude;
    private double longitude;
    private String category;
}
