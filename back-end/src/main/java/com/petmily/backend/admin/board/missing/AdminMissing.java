package com.petmily.backend.admin.board.missing;

import java.time.LocalDateTime;

import com.petmily.backend.community.missing.board.MissingBoard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminMissing {
	private Long boardNum;
	private Long memberNum;
	private String boardId;
	private String subject;
	private String content;
	private String thumbnail;
	private int count;
	private LocalDateTime postDate;
	
	// 추가 정보
	private String boardName;
	private String boardSpecies;
	private String boardLocation;
	private int boardAge;
	private String boardGender;
	private Boolean boardStatus;
	
	public static AdminMissing MissingBoardToAdminMissing(MissingBoard missingBoard) {
		return AdminMissing.builder()
				.boardNum(missingBoard.getBoardNum())
				.memberNum(missingBoard.getMemberNum())
				.boardId(missingBoard.getBoardId())
				.subject(missingBoard.getBoardSubject())
				.content(missingBoard.getBoardContent())
				.thumbnail(missingBoard.getImgThumbnail())
				.count(missingBoard.getBoardCount())
				.postDate(missingBoard.getBoardDate())
				.boardName(missingBoard.getBoardName())
				.boardSpecies(missingBoard.getBoardSpecies())
				.boardLocation(missingBoard.getBoardLocation())
				.boardAge(missingBoard.getBoardAge())
				.boardGender(missingBoard.getBoardGender())
				.boardStatus(missingBoard.getBoardStatus())
				.build();
	}
}
