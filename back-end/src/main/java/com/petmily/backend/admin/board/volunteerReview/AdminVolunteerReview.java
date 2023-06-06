package com.petmily.backend.admin.board.volunteerReview;

import java.time.LocalDateTime;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface AdminVolunteerReview {
	public Long getBoardNum();
	public Long getMemberNum();
	public String getMemberId();
	public String getBoardId();
	public String getSubject();
	public String getContent();
//	public String getThumbnail();
	public Integer getCount();
	public LocalDateTime getPostDate();
	
}
