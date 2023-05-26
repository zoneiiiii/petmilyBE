package com.petmily.backend.adopt.dto;

import java.time.LocalDate;

public interface ReviewBoardList {
	Long getBoardNum();
	String getReviewSubject();
	Integer getReviewCount();
	LocalDate getReviewDate();
	String getMemberNickName();
	String getImgThumbnail();

}
