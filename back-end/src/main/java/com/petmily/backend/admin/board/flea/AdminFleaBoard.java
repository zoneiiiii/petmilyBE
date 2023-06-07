package com.petmily.backend.admin.board.flea;

import java.time.LocalDateTime;

public interface AdminFleaBoard {
	public Long getBoardNum();
	public Long getMemberNum();
	public String getMemberId();
	public String getBoardId();
	public String getSubject();
	public String getContent();
//	public String getThumbnail();
	public Integer getCount();
	public LocalDateTime getPostDate();
//	public String getCost();
	
}
