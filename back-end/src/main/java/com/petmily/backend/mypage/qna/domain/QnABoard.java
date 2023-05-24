package com.petmily.backend.mypage.qna.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	private Long memberNum;

	private String boardId;

	private String qnaSubject;
	
	@Column(columnDefinition = "LONGTEXT")
	private String qnaContent;

	private Boolean qnaStatus;
	
	@Column(length = 1000)
	private String qnaImg;
	
	@Temporal(TemporalType.DATE)
	private Date qnaDate;


}