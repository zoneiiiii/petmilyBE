package com.petmily.backend.community.free.board;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@Builder
@Entity
@Table(name="freeboard")
public class FreeBoard {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column
    private Long memberNum;

    @Column
    private String boardId;

    @Column
    private String freeSubject;

    @Column
    private String freeContent;

    @Column
    private Integer freeCount;

//    @CreationTimestamp
    @Column
    private LocalDateTime freeDate;

    @Column
    private String imgThumbnail;
}
