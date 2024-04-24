package com.location.locationmanager.Dto.LocationDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SearchDto {
    private double latitude;
    private double longitude;
    private double range;
    private double minRange;
    private String category;

    private double minLatitudeRange;
    private double maxLatitudeRange;
    private double minLongitudeRange;
    private double maxLongitudeRange;

    public void calculateRange() {
        maxLatitudeRange = 1.0 / 111000.0 * range;
        minLatitudeRange = 1.0 / 111000.0 * minRange;
        maxLongitudeRange = Math.cos(Math.toRadians(latitude)) * maxLatitudeRange;
        minLongitudeRange = Math.cos(Math.toRadians(latitude)) * minLongitudeRange;

        log.info("maxLati = {}, maxLongi = {}", maxLatitudeRange, maxLongitudeRange);
        log.info("minLati = {}, minLongi = {}", minLatitudeRange, minLongitudeRange);
    }
}
