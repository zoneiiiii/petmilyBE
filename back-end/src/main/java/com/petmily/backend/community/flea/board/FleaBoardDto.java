package com.petmily.backend.community.flea.board;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FleaBoardDto {
    private Long boardNum;
    private Long memberNum;
    private String boardId;
    private Integer boardCount;
    private LocalDateTime boardDate;
    private String boardSubject;
    private String boardContent;
    private String boardCost;
    private String boardCategory;
    private Boolean boardStatus;
    private String imgThumbnail;
}
