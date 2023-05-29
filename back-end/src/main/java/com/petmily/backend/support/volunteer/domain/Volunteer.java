package com.petmily.backend.support.volunteer.domain;


import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column(nullable = false)
    private Long memberNum;

    @Column(nullable = false)
    private String boardId;

    @Column(nullable = false)
    private String shelterName;

    @Column(nullable = false)
    private Integer volunteerNumber;

    @Column(nullable = false)
    private String volunteerAge;

    @Column(nullable = false)
    private String volunteerAddr;

    @Column(nullable = false)
    private String volunteerAddrDetail;

    @Column(nullable = false)
    private String volunteerSubject;

    @Column(nullable = false)
    private String volunteerContent;

    @Column(nullable = false)
    private Integer volunteerCount;

    @Column(nullable = false)
    private LocalDateTime volunteerDate;

    @Column(nullable = false)
    private String imgThumbnail;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date volunteerStartPeriod;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date volunteerEndPeriod;

    @Column(nullable = false)
    private Boolean volunteerStatus;


}
