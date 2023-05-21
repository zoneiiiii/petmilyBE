package com.petmily.backend.community.free.board;

import java.time.LocalDate;

public interface FreeBoardList {

	Long getBoardNum();
	String getFreeSubject();
	Integer getFreeCount();
	LocalDate getFreeDate();
	String getMemberNickName();
}
