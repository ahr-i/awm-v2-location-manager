package com.example.location_server.Repository.CommunityRepository;

import com.example.location_server.JpaClass.CommunityTable.UserPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserPostRepository extends JpaRepository<UserPostEntity, Integer> {
// JPQL 쿼리를 사용하여 content 필드만 선택
    @Query("SELECT u.content FROM UserPostEntity u WHERE u.location.locationId = :locationId ORDER BY u.likeCount DESC")
    List<String> findTop10ContentByLocationIdOrderByLikeCountDesc(@Param("locationId") int locationId);
}
