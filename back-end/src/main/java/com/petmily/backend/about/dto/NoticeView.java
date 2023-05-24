package com.petmily.backend.about.dto;

import java.time.LocalDateTime;

import com.petmily.backend.about.domain.Notice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface NoticeView {
	Long getNo();
	String getImgSrc();
	String getNickname();
	String getSubject();
	String getContent();
	int getCount();
	LocalDateTime getPostDate();
	Long getPrevNo();
	String getPrevSub();
	Long getNextNo();
	String getNextSub();
}
