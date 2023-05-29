package com.petmily.backend.support.volunteerReview.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolunteerReviewDto {

    private Long boardNum;
    private Long memberNum;
    private String boardId;
    private String reviewSubject;
    private String reviewContent;
    private Integer reviewCount;
    private LocalDateTime reviewDate;
    private String imgThumbnail;
}
