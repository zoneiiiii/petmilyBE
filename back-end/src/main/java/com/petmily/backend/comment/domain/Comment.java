package com.petmily.backend.comment.domain;

import com.petmily.backend.member.login.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="comment")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNum;

    @ManyToOne
    @JoinColumn (name = "memberNum", nullable = false)
    private Member member;

    private String boardId;

    private Long boardNum;

    private String commentContent;

    @CreatedDate
    private LocalDateTime commentCreate;

    @LastModifiedDate
    private LocalDateTime commentUpdate;

    private Integer commentPnum;

    private Boolean commentIsSecret;

    @Builder
    public Comment(Member member,String boardId, Long boardNum, String commentContent, Boolean commentIsSecret, Integer commentPnum ){
        this.member = member;
        this.boardId = boardId;
        this.boardNum = boardNum;
        this.commentContent = commentContent;
        this.commentIsSecret = commentIsSecret;
        this.commentPnum = commentPnum;
    }

    public void updateCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void updateCommentIsSecret(Boolean commentIsSecret) {
        this.commentIsSecret = commentIsSecret;
    }
}
