package com.petmily.backend.about.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface EventView {
	Long getNo();
	String getImgSrc();
	String getNickname();
	String getSubject();
	String getContent();
	String getThumbnail();
	int getCount();
	LocalDateTime getPostDate();
	LocalDate getStartDate();
	LocalDate getEndDate();
	Long getPrevNo();
	String getPrevSub();
	Long getNextNo();
	String getNextSub();
}
