package com.petmily.backend.admin.board.volunteer;

import java.time.LocalDateTime;
import java.util.Date;

import com.petmily.backend.support.volunteer.domain.Volunteer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminVolunteer {
	private Long boardNum;
	private Long memberNum;
	private String boardId;
	private String subject;
	private String content;
	private String thumbnail;
	private int count;
	private LocalDateTime postDate;
	
	private String shelterName;
	private Integer volunteerNumber;
	private String volunteerAge;
	private String volunteerAddr;
	private String volunteerAddrDetail;
	private Date volunteerStartPeriod;
	private Date volunteerEndPeriod;
	private Boolean volunteerStatus;
	
	public static AdminVolunteer VolunteerToAdminVolunteer(Volunteer volunteer) {
		return AdminVolunteer.builder()
				.boardNum(volunteer.getBoardNum())
				.memberNum(volunteer.getMemberNum())
				.boardId(volunteer.getBoardId())
				.subject(volunteer.getVolunteerSubject())
				.content(volunteer.getVolunteerContent())
				.thumbnail(volunteer.getImgThumbnail())
				.count(volunteer.getVolunteerCount())
				.postDate(volunteer.getVolunteerDate())
				.shelterName(volunteer.getShelterName())
				.volunteerNumber(volunteer.getVolunteerNumber())
				.volunteerAge(volunteer.getVolunteerAge())
				.volunteerAddr(volunteer.getVolunteerAddr())
				.volunteerAddrDetail(volunteer.getVolunteerAddrDetail())
				.volunteerStartPeriod(volunteer.getVolunteerStartPeriod())
				.volunteerEndPeriod(volunteer.getVolunteerEndPeriod())
				.volunteerStatus(volunteer.getVolunteerStatus())
				.build();
	}
}
