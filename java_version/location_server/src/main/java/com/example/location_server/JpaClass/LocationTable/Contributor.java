package com.example.location_server.JpaClass.LocationTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contributorId;
    @Column
    private String userId;
    @Column
    private int locationId;
    @Column
    private int rate;
}
