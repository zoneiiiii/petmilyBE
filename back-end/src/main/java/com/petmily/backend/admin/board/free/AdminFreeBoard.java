package com.petmily.backend.admin.board.free;

import java.time.LocalDateTime;

import com.petmily.backend.community.free.board.FreeBoard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminFree {
	private Long boardNum;
	private Long memberNum;
	private String boardId;
	private String subject;
	private String content;
	private String thumbnail; // 니가 왜 있어!
	private int count;
	private LocalDateTime postDate;
	
	public static AdminFree FreeBoardToAdminFree(FreeBoard freeBoard) {
		return AdminFree.builder()
				.boardNum(freeBoard.getBoardNum())
				.memberNum(freeBoard.getMemberNum())
				.boardId(freeBoard.getBoardId())
				.subject(freeBoard.getFreeSubject())
				.content(freeBoard.getFreeContent())
				.thumbnail(freeBoard.getImgThumbnail())
				.count(freeBoard.getFreeCount())
				.postDate(freeBoard.getFreeDate())
				.build();
	}
}
