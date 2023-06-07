package com.petmily.backend.adopt.adoptReview;

import java.time.LocalDateTime;

public interface ReviewBoardDetail {
	Long getBoardNum();
	Long getMemberNum();
	String getBoardId();
	String getReviewSubject();
	String getReviewContent();
	Integer getReviewCount();
	LocalDateTime getReviewDate();
	String getImgThumbnail();
	String getMemberNickName();
	String getMemberImg();
}
