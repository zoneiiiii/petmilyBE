package com.petmily.backend.mypage.qna.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="qnaBoard")
public class QnABoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNum;

	@Column(nullable = false)
	private Long memberNum;

	@Column(nullable = false)
	private String boardId;

	@Column(nullable = false)
	private String qnaSubject;
	
	@Lob
	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String qnaContent;

	@Column(nullable = false)
	private Boolean qnaStatus;
	
	@Column(length = 1000)
	private String qnaImg;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date qnaDate;
	
//	@Lob
//	@Column(columnDefinition = "LONGTEXT", nullable = false)
//	private String adminAnswer;


}