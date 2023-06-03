package com.petmily.backend.mypage.adoptlist;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class adoptList {
	private Long petNum;
    private Long memberNum;
    private String petName;
    private String petAge;
    private String petImg;
    private String petSpecies;
    private String shelterName;
    private String shelterTel;
    private String shelterAddr;
    private LocalDateTime shelterDate;
    private String sexCd;
    private String neuterYn;
    
    
}
