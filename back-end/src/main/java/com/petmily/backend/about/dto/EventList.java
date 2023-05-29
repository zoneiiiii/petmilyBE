package com.petmily.backend.about.dto;

import java.time.LocalDateTime;

import com.petmily.backend.about.domain.EventBoard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventList {
	private Long num;
	private String writer;
	private String subject;
	private String thumbnail;
	private int count;
	private LocalDateTime postDate;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	public static EventList EventToListDto(EventBoard event) {
		return EventList.builder()
				.num(event.getBoardNum())
				.writer(event.getMember().getMemberNickname())
				.subject(event.getSubject())
				.count(event.getCount())
				.thumbnail(event.getThumbnail())
				.postDate(event.getPostDate())
				.startDate(event.getStartDate())
				.endDate(event.getEndDate())
				.build();
	}
}
