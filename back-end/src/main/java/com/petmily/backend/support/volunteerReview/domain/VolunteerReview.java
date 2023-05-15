package com.petmily.backend.support.volunteerReview.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
