package com.petmily.backend.member.login.dto;
import lombok.Data;
@Data
public class MemberUpdateRequest {
	private Long memberNum;
	private String memberPw;
    private String memberNickname;
    private String memberEmail;
    private String memberTel;
    private String memberImg;
}
