package com.petmily.backend.support.volunteerReview.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
