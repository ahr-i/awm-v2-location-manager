package com.example.location_server.JpaClass.LocationTable;

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
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}
