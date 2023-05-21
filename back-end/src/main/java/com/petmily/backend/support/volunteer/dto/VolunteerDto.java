package com.petmily.backend.support.volunteer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class VolunteerDto {

    private Long boardNum;
    private Long memberNum;
    private String boardId;
    private String shelterName;
    private Integer volunteerNumber;
    private String volunteerAge;
    private String volunteerAddr;
    private String volunteerAddrDetail;
    private String volunteerSubject;
    private String volunteerContent;
    private Integer volunteerCount;
    private LocalDateTime volunteerDate;
    private String imgThumbnail;
    private Date volunteerStartPeriod;
    private Date volunteerEndPeriod;
    private Boolean volunteerStatus;

}
