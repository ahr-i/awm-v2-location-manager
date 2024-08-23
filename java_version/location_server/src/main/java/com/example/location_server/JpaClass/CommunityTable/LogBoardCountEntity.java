package com.example.location_server.JpaClass.CommunityTable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class LogBoardCountEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String userId;
    @CreationTimestamp
    private LocalDateTime createTime;
    @ManyToOne
    @JoinColumn(name = "postId")
    private UserPostEntity logBoardEntity;
    private int countCheck;

    public static LogBoardCountEntity likeCount(String userId, UserPostEntity countEntity){
        LogBoardCountEntity entity = new LogBoardCountEntity();
        entity.setUserId(userId);
        entity.setCountCheck(1);
        entity.setLogBoardEntity(countEntity);
        return entity;
    }
    public static LogBoardCountEntity BadCount(UserPostEntity userPostEntity, String userId){
        LogBoardCountEntity countEntity = new LogBoardCountEntity();

        countEntity.setCountCheck(0);
        countEntity.setLogBoardEntity(userPostEntity);
        countEntity.setUserId(userId);

        return countEntity;
    }
}
