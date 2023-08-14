package com.petmily.backend.mypage.qna.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class QnADto {
	private Long boardNum;
	private Long memberNum;
	private String boardId;
	private String qnaSubject;
	private String qnaContent;
	private Boolean qnaStatus;
	private String qnaImg;
	private Date qnaDate;
	private String adminAnswer;
	
	private String memberId;
}