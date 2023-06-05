package com.petmily.backend.admin.board.find;

import java.time.LocalDateTime;

import com.petmily.backend.community.find.board.FindBoard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface AdminFindBoard {
	public Long getBoardNum();
	public Long getMemberNum();
	public String getMemberId();
	public String getBoardId();
	public String getSubject();
	public String getContent();
	public String getThumbnail();
	public Integer getCount();
	public LocalDateTime getPostDate();
//	
//	public String getBoardSpecies();
//	public String getBoardLocation();
//	public Integer getBoardAge();
//	public String getBoardGender();
	
}
