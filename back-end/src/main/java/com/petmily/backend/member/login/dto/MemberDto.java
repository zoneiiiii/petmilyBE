package com.petmily.backend.member.login.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String memberId;
    private String memberPw;
    private String memberEmail;
}
