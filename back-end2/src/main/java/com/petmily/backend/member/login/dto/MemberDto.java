package com.petmily.backend.member.login.dto;

import java.util.Date;

import org.jetbrains.annotations.NotNull;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class MemberDto {
	@NotNull
	private String memberId;
	@NotNull
    private String memberPw;
	@NotNull
    private String memberNickname;
	@NotNull
    private String memberEmail;
	@NotNull
    private String memberName;
	@NotNull
    private String memberGender;
	@NotNull
    @Temporal(TemporalType.DATE)
    private Date memberBirth;
	@NotNull
    private String memberTel;
    private String memberRole;
    private String memberImg;
}
