package com.petmily.backend.community.missing.board;

import java.time.LocalDateTime;

public interface MissingBoardDetail {
	Long getBoardNum();
	Long getMemberNum();
	String getBoardId();
	String getBoardSubject();
	String getBoardContent();
	Integer getBoardCount();
	LocalDateTime getBoardDate();
	Boolean getBoardStatus();
	String getBoardName();
	String getBoardLocation();
	String getBoardSpecies();
	String getBoardGender();
	Integer getBoardAge();
	String getImgThumbnail();
	String getMemberNickName();
	String getMemberImg();
}
