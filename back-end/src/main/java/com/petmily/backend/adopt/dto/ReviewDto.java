package com.petmily.backend.adopt.dto;


import java.time.LocalDateTime;
import java.util.Date;

import com.petmily.backend.adopt.domain.ReviewBoard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewDto {
	 private Long boardNum;
	 private Long memberNum;
	 private String boardId;
	 private String reviewSubject;
	 private String reviewContent;
	 private int reviewCount;
	 private String imgThumbnail;
	 private LocalDateTime reviewDate;

}
