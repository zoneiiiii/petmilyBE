package com.petmily.backend.member.login.dto;

import lombok.Data;

@Data
public class MemberRoleUpdateDto {
    private Long memberNum;
    private String memberRole;
}
