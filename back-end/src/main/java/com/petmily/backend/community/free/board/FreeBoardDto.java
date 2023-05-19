package com.petmily.backend.community.free.board;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeBoardDto {
    private Long boardNum;
    private Long memberNum;
    private String boardId;
    private String freeSubject;
    private String freeContent;
    private Integer freeCount;
    private LocalDateTime freeDate;
    private String imgThumbnail;
}
