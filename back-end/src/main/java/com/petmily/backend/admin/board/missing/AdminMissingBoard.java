package com.petmily.backend.admin.board.missing;

import java.time.LocalDateTime;

public interface AdminMissingBoard {
	public Long getBoardNum();
	public Long getMemberNum();
	public String getMemberId();
	public String getBoardId();
	public String getSubject();
	public String getContent();
//	public String getThumbnail();
	public Integer getCount();
	public LocalDateTime getPostDate();
//	
//	public String getBoardName();
//	public String getBoardSpecies();
//	public String getBoardLocation();
//	public Integer getBoardAge();
//	public String getBoardGender();
//	public Boolean getBoardStatus();
}
