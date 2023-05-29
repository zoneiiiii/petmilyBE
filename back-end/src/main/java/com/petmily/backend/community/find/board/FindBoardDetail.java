package com.petmily.backend.community.find.board;

import java.time.LocalDateTime;

public interface FindBoardDetail {
	Long getBoardNum();
	Long getMemberNum();
	String getBoardId();
	String getBoardSubject();
	String getBoardContent();
	Integer getBoardCount();
	LocalDateTime getBoardDate();
	String getBoardLocation();
	String getBoardSpecies();
	String getBoardGender();
	Integer getBoardAge();
	String getImgThumbnail();
	String getMemberNickName();
}
