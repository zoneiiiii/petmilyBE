package com.petmily.backend.admin.board.volunteer;

import java.time.LocalDateTime;
import java.util.Date;

import com.petmily.backend.support.volunteer.domain.Volunteer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface AdminVolunteer {
	public Long getBoardNum();
	public Long getMemberNum();
	public String getMemberId();
	public String getBoardId();
	public String getSubject();
	public String getContent();
//	public String getThumbnail();
	public Integer getCount();
	public LocalDateTime getPostDate();
	
//	public String getShelterName();
//	public Integer getVolunteerNumber();
//	public String getVolunteerAge();
//	public String getVolunteerAddr();
//	public String getVolunteerAddrDetail();
//	public Date getVolunteerStartPeriod();
//	public Date getVolunteerEndPeriod();
//	public Boolean getVolunteerStatus();
}
