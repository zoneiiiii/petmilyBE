package com.petmily.backend.admin.board.event;

import java.time.LocalDateTime;

import com.petmily.backend.about.domain.EventBoard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminEventBoard {
	private Long boardNum;
	private Long memberNum;
	private String memberId;
	private String memberNickname;
	private String memberImg;
	private String boardId;
	private String subject;
	private String content;
	private String imgThumbnail;
	private int count;
	private LocalDateTime postDate;
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	static AdminEventBoard EventBoardToAdminEventBoard(EventBoard eventBoard) {
		return AdminEventBoard.builder()
				.boardNum(eventBoard.getBoardNum())
				.memberNum(eventBoard.getMember().getMemberNum())
				.memberId(eventBoard.getMember().getMemberId())
				.memberImg(eventBoard.getMember().getMemberImg())
				.memberNickname(eventBoard.getMember().getMemberNickname())
				.boardId(eventBoard.getCategory())
				.subject(eventBoard.getSubject())
				.content(eventBoard.getContent())
				.imgThumbnail(eventBoard.getThumbnail())
				.count(eventBoard.getCount())
				.postDate(eventBoard.getPostDate())
				.startDate(eventBoard.getStartDate())
				.endDate(eventBoard.getEndDate())
				.build();
	}
}
