package com.petmily.backend.support.volunteerReview.domain;


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
@Table(name="volunteerReview")
public class VolunteerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column
    private Long memberNum;

    @Column
    private String boardId;

    @Column
    private String reviewSubject;

    @Column
    private String reviewContent;

    @Column
    private Integer reviewCount;

    @Column
    private LocalDateTime reviewDate;

    @Column
    private String imgThumbnail;

}
