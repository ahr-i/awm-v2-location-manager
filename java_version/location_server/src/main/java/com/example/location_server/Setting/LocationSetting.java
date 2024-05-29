package com.example.location_server.Setting;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class LocationSetting {
    private final int userRate;
    private final double latitudeRange;
    private final int threshold;
    private final int registerBaseScore;
    private final int deleteBaseScore;

    public LocationSetting() {
        userRate = 5;
        latitudeRange = 1.0 / 111000.0 * 10;
        threshold = 0;
        registerBaseScore = 100;
        deleteBaseScore = -50;
    }

    public double calculateLongitudeRange(double latitude) {
        return Math.cos(Math.toRadians(latitude)) * latitudeRange;
    }
}
