package com.petmily.backend.mypage.Info;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfo {
	private Long memberNum;
    private String memberImg;
    private String memberId;
    private String memberNickname;
    private String memberName;
    private String memberEmail;
    private String memberGender;
    private Date memberBirth;
    private String memberTel;
}
