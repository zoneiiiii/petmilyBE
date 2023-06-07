package com.petmily.backend.admin.board.notice;

import java.time.LocalDateTime;

import com.petmily.backend.about.domain.Notice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminNotice {
	private Long boardNum;
	private Long memberNum;
	private String memberId;
	private String memberNickname;
	private String memberImg;
	private String boardId;
	private String subject;
	private String content;
	private int count;
	private LocalDateTime postDate;	
	
	public static AdminNotice NoticeToAdminNotice(Notice notice) {
		return AdminNotice.builder()
				.boardNum(notice.getBoardNum())
				.memberNum(notice.getMember().getMemberNum())
				.memberId(notice.getMember().getMemberId())
				.memberNickname(notice.getMember().getMemberNickname())
				.memberImg(notice.getMember().getMemberImg())
				.boardId(notice.getCategory())
				.subject(notice.getSubject())
				.content(notice.getContent())
				.count(notice.getCount())
				.postDate(notice.getPostDate())
				.build();
	}
}
