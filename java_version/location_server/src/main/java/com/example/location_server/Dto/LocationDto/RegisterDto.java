package com.example.location_server.Dto.LocationDto;

import com.example.location_server.JpaClass.LocationTable.Contributor;
import com.example.location_server.JpaClass.LocationTable.Location;
import com.example.location_server.JpaClass.LocationTable.LocationImage;
import lombok.Data;

import java.util.Base64;

@Data
public class RegisterDto {
    private double latitude;
    private double longitude;
    private String userId;
    private String category;
    private String title;
    private String description;
    private String image;
    private int locationId;

    static public Location toLocation(RegisterDto dto) {
        Location location = new Location();

        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setCategory(dto.getCategory());
        location.setTitle(dto.getTitle());
        location.setDescription(dto.getDescription());

        return location;
    }

    static public Contributor toContributor(RegisterDto dto) {
        Contributor contributor = new Contributor();

        contributor.setUserId(dto.getUserId());

        return contributor;
    }

    static public LocationImage toLocationImage(RegisterDto dto) {
        LocationImage locationImage = new LocationImage();

        locationImage.setImage(Base64.getDecoder().decode(dto.getImage()));

        return locationImage;
    }
}
