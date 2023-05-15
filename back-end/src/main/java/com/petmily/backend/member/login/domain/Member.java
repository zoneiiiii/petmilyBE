package com.petmily.backend.member.login.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name="member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNum;

    private String memberId;
    private String memberPw;
    private String memberNickname;
    private String memberEmail;
    private String memberName;
    private String memberGender;
    @Temporal(TemporalType.DATE)
    private Date memberBirth;
    private String memberTel;
    private String memberAddr;
    private String memberRole;
    private String memberImg;

}
