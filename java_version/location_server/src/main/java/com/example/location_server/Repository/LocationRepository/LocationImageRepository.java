package com.example.location_server.Repository.LocationRepository;

import com.example.location_server.JpaClass.LocationTable.LocationImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface LocationImageRepository extends JpaRepository<LocationImage,Integer> {
    @Modifying
    @Query("select l.image from LocationImage l where l.locationId = :locationId")
    List<byte[]> findByLocationId(@Param("locationId") int locationId);
}
