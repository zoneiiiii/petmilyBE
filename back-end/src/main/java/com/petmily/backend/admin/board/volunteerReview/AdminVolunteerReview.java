package com.petmily.backend.admin.board.volunteerReview;

import java.time.LocalDateTime;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminVolunteerReview {
	private Long boardNum;
	private Long memberNum;
	private String boardId;
	private String subject;
	private String content;
	private String thumbnail;
	private int count;
	private LocalDateTime postDate;
	
	public static AdminVolunteerReview VolunteerToAdminVolunteerReview(VolunteerReview volunteerReview) {
		return AdminVolunteerReview.builder()
				.boardNum(volunteerReview.getBoardNum())
				.memberNum(volunteerReview.getMemberNum())
				.boardId(volunteerReview.getBoardId())
				.subject(volunteerReview.getReviewContent())
				.content(volunteerReview.getReviewContent())
				.thumbnail(volunteerReview.getImgThumbnail())
				.count(volunteerReview.getReviewCount())
				.postDate(volunteerReview.getReviewDate())
				.build();
	}
}
