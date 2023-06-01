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
@Table(name="notice")
public class Notice {
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
	private int count;
	private LocalDateTime postDate;
	
	@Builder
	public Notice(Long boardNum, String category, Member member, String subject, String content, int count, LocalDateTime postDate) {
		this.boardNum = boardNum;
		this.member = member;
		this.category = category;
		this.subject = subject;
		this.content = content;
		this.count = count;
		this.postDate = postDate;
	}
	
//	  no: 101,
//    memberNo: 1,
//    category: "notice",
//    subject: "4월 펫밀리 기부내역",
//    contents: "4월 많은 분들이 기부해주셨습니다!",
//    count: 31,
//    postDate: "2023-05-05",
}
