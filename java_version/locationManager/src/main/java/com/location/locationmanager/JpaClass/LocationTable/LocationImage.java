package com.location.locationmanager.JpaClass.LocationTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LocationImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;
    @Column
    private int locationId;
    @Lob
    private byte[] image;
}