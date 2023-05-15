package com.petmily.backend.support.volunteer.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Date;

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
