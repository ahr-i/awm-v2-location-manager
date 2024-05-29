package com.example.location_server.JpaClass.CommunityTable;

import com.example.location_server.JpaClass.LocationTable.Location;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "board_table")
@Entity
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column(length = 20)
    private String boardWriter;
    @Column
    private String boardTitle;
    @Column
    private String boardContent;
    @Column
    private int boardHits;
    @Column
    private String userId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    public Location location;
    @Column
    private int reportCount;
    @Column
    int likeCount;
    @JsonManagedReference
    @OneToMany(mappedBy ="entity",cascade = CascadeType.REMOVE,orphanRemoval = true)
    List<CommentEntity> entityList = new ArrayList<>();
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageFile;

}
