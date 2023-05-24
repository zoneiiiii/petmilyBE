package com.petmily.backend.about.dto;

import java.time.LocalDateTime;

import com.petmily.backend.about.domain.Notice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeList {
	private Long num;
	private String writer;
	private String subject;
	private int count;
	private LocalDateTime postDate;
	
	public static NoticeList NoticetoListDto(Notice notice) {
		return NoticeList.builder()
				.num(notice.getBoardNum())
				.writer(notice.getMember().getMemberNickname())
				.subject(notice.getSubject())
				.count(notice.getCount())
				.postDate(notice.getPostDate())
				.build();
	}
	
}
