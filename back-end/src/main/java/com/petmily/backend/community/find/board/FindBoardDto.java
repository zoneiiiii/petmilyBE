package com.petmily.backend.community.find.board;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBoardDto {
	private Long boardNum;
	private Long memberNum;
	private String boardId;
	private String boardSubject;
	private String boardContent;
	private Integer boardCount;
	private LocalDateTime boardDate;
	private String boardLocation;
	private String boardSpecies;
	private String boardGender;
	private String imgThumbnail;
	private Integer boardAge;
}
