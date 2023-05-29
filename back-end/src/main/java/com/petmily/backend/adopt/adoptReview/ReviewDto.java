package com.petmily.backend.adopt.adoptReview;


import java.time.LocalDateTime;

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
