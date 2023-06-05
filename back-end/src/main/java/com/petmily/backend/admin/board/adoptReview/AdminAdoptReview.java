package com.petmily.backend.admin.board.adoptReview;

import java.time.LocalDateTime;


public interface AdminAdoptReview {
	public Long getBoardNum();
	public Long getMemberNum();
	public String getMemberId();
	public String getBoardId();
	public String getSubject();
	public String getContent();
	public String getThumbnail();
	public Integer getCount();
	public LocalDateTime getPostDate();
	
}
