package com.petmily.backend.admin.board.free;

import java.time.LocalDateTime;

public interface AdminFreeBoard {
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
