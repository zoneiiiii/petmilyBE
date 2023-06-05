package com.petmily.backend.admin.board.flea;

import java.time.LocalDateTime;

import com.petmily.backend.community.flea.board.FleaBoard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminFlea {
	private Long boardNum;
	private Long memberNum;
	private String boardId;
	private String subject;
	private String content;
	private String thumbnail;
	private int count;
	private LocalDateTime postDate;
	private String cost;
	
	public static AdminFlea FleaBoardToAdminFlea(FleaBoard fleaBoard) {
		return AdminFlea.builder()
				.boardNum(fleaBoard.getBoardNum())
				.memberNum(fleaBoard.getMemberNum())
				.boardId(fleaBoard.getBoardId())
				.subject(fleaBoard.getBoardSubject())
				.content(fleaBoard.getBoardContent())
				.thumbnail(fleaBoard.getImgThumbnail())
				.count(fleaBoard.getBoardCount())
				.postDate(fleaBoard.getBoardDate())
				.cost(fleaBoard.getBoardCost())
				.build();
	}
}
