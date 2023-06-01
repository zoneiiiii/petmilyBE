package com.petmily.backend.about.domain;

import java.time.LocalDateTime;

import com.petmily.backend.member.login.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="eventboard")
public class EventBoard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long boardNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberNum")
	private Member member;
	
	private String category;
	private String subject;
	
	@Lob
	@Column(name = "content", columnDefinition = "LONGTEXT")
	private String content;
	@Lob
	@Column(name = "thumbnail", columnDefinition = "LONGTEXT")
	private String thumbnail;
	private int count;
	private LocalDateTime postDate;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	@Builder
	public EventBoard(Long boardNum, String category, Member member, 
			String subject, String content, String thumbnail, int count, 
			LocalDateTime postDate, LocalDateTime startDate, LocalDateTime endDate) {
		this.boardNum = boardNum;
		this.member = member;
		this.category = category;
		this.subject = subject;
		this.content = content;
		this.thumbnail = thumbnail;
		this.count = count;
		this.postDate = postDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
