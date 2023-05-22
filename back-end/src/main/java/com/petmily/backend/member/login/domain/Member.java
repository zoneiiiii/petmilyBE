package com.petmily.backend.member.login.domain;

import java.util.Date;

import org.jetbrains.annotations.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long memberNum;
	
    private String memberRole;
    private String memberImg;
    private String memberId;
    private String memberPw;
    private String memberNickname;
    private String memberEmail;
    private String memberName;
    private String memberGender;
    @Temporal(TemporalType.DATE)
    private Date memberBirth;
    private String memberTel;

}
