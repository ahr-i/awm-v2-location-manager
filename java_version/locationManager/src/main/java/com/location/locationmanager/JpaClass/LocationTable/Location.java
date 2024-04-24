package com.location.locationmanager.JpaClass.LocationTable;

import com.location.locationmanager.JpaClass.CommunityTable.BoardEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;
    @Column
    private String category;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private int score;
    @Column
    private int visitCount;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "location" , cascade = CascadeType.REMOVE ,orphanRemoval = true)
    private List<BoardEntity> entityList = new ArrayList<>();
    @Column
    private String title;
    @Column
    private String description;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
