package com.petmily.backend.community.find.board;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="findboard")
public class FindBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column
    private Long memberNum;

    @Column
    private String boardId;

    @Column
    private String boardSubject;

    @Column
    private String boardContent;

    @Column
    private Integer boardCount;

    @Column
    private LocalDateTime boardDate;
    
    @Column
    private String boardLocation;
    
    @Column
    private String boardSpecies;
    
    @Column
    private int boardAge;
    
    @Column
    private String boardGender;

    @Column
    private String imgThumbnail;
}
