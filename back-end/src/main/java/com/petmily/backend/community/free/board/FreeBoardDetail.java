package com.petmily.backend.community.free.board;

import java.time.LocalDateTime;

public interface FreeBoardDetail {
	Long getBoardNum();
	Long getMemberNum();
	String getBoardId();
	String getFreeSubject();
	String getFreeContent();
	Integer getFreeCount();
	LocalDateTime getFreeDate();
	String getImgThumbnail();
	String getMemberNickName();
	String getMemberImg();
}
