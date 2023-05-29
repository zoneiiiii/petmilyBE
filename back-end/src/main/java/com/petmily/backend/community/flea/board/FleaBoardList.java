package com.petmily.backend.community.flea.board;

import java.time.LocalDateTime;

public interface FleaBoardList {
    Long getBoardNum();
    String getBoardId();
    Integer getBoardCount();
    LocalDateTime getBoardDate();
    String getBoardSubject();
    String getBoardCost();
    String getBoardCategory();
    Boolean getBoardStatus();
    String getImgThumbnail();
	String getMemberNickName();
}
