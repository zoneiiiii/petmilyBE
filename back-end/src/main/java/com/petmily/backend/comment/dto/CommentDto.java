package com.petmily.backend.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long commentNum;
    private Long memberNum;
    private String boardId;
    private Long boardNum;
    private String commentContent;
    private LocalDateTime commentCreate;
    private LocalDateTime commentUpdate;
    private Integer commentPnum;
    private Boolean commentIsSecret;

    private String memberImg;
    private String memberNickname;
}
