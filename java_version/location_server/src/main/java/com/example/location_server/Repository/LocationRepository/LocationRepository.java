package com.example.location_server.Repository.LocationRepository;

import com.example.location_server.JpaClass.LocationTable.Location;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface LocationRepository extends JpaRepository<Location,Integer> {
    @Modifying
    @Query("update Location l set l.score = l.score + :score where l.locationId = :locationId")
    void updateScore(@Param("locationId") int locationId, @Param("score") int score);

    // 해당 장소의 visitCount를 증가
    @Modifying
    @Query("update Location l set l.visitCount = l.visitCount + 1 where l.locationId = :locationId")
    void upVisitCount(@Param("locationId") int locationId);

    // range에 따른 범위 검색
    @Modifying
    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :latitudeRange) AND (:latitude + :latitudeRange) " +
            "AND l.longitude BETWEEN (:longitude - :longitudeRange) AND (:longitude + :longitudeRange) " +
            "AND l.score >= :score")
    List<Location> findLocationsInRange(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("latitudeRange") double latitudeRange, @Param("longitudeRange") double longitudeRange, @Param("score") int score);

    // Max Range와 Min Range 범위를 계산해 장소 검색
    @Modifying
    @Query("SELECT l FROM Location l WHERE (l.latitude BETWEEN (:latitude - :maxLatitudeRange) AND (:latitude - :minLatitudeRange) " +
            "OR l.latitude BETWEEN (:latitude + :minLatitudeRange) AND (:latitude + :maxLatitudeRange)) " +
            "AND (l.longitude BETWEEN (:longitude - :maxLongitudeRange) AND (:longitude - :minLongitudeRange) " +
            "OR l.longitude BETWEEN (:longitude + :minLongitudeRange) AND (:longitude + :maxLongitudeRange)) " +
            "AND l.score >= :score")
    List<Location> findLocationsWithinRange(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("maxLatitudeRange") double maxLatitudeRange, @Param("maxLongitudeRange") double maxLongitudeRange, @Param("minLatitudeRange") double minLatitudeRange, @Param("minLongitudeRange") double minLongitudeRange, @Param("score") int score);

    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :latitudeRange) AND (:latitude + :latitudeRange) " +
            "AND l.longitude BETWEEN (:longitude - :longitudeRange) AND (:longitude + :longitudeRange) " +
            "AND l.category = :category")
    List<Location> findLocationByCategoryInRange(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("latitudeRange") double latitudeRange, @Param("longitudeRange") double longitudeRange, @Param("category") String category);

    @Query("SELECT l FROM Location l WHERE l.latitude = :latitude AND l.longitude = :longitude AND l.category = :category")
    List<Location> findLocationByCategory(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("category") String category);

    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :latitudeRange) AND (:latitude + :latitudeRange) " +
            "AND l.longitude BETWEEN (:longitude - :longitudeRange) AND (:longitude + :longitudeRange) " +
            "AND l.score >= :score AND l.category = :category ORDER BY l.visitCount DESC")
    List<Location> findTopLocationInRange(@Param("latitude") double latitude,
                                          @Param("longitude") double longitude,
                                          @Param("latitudeRange") double latitudeRange,
                                          @Param("longitudeRange") double longitudeRange,
                                          @Param("score") int score,
                                          @Param("category") String category,
                                          Pageable pageable);
}
