package com.petmily.backend.adopt.dto;


import java.time.LocalDateTime;
import java.util.Date;

import com.petmily.backend.adopt.domain.Review;

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
	 
	 public Review toEntity() {
		 Review build = Review.builder()
				 .memberNum(memberNum)
				 .boardId(boardId)
				 .reviewSubject(reviewSubject)
				 .reviewContent(reviewContent)
				 .imgThumbnail(imgThumbnail)
				 .build();
		 return build;
	 }
	 
	    @Builder
	    public ReviewDto(Long boardNum, Long memberNum, String boardId, String reviewSubject, String reviewContent, String imgThumbnail) {
	        this.boardNum = boardNum;
	        this.memberNum = memberNum;
	        this.boardId = boardId;
	        this.reviewSubject = reviewSubject;
	        this.reviewContent = reviewContent;
	        this.imgThumbnail = imgThumbnail;
	    }

}
