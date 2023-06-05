package com.petmily.backend.mypage.adoptlist;

import java.time.LocalDateTime;

import com.petmily.backend.adopt.adoptInfo.Adopt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdoptListResponse {
	private Long adoptNum;
	 private Long memberNum;
	 private String petName;
	 private String petImg;
	 private String petAge;
	 private String petSpecies;
	 private String sexCd;
	 private String neuterYn;
	 private String shelterName;
	 private String shelterTel;
	 private String shelterAddr;
	 private LocalDateTime adoptDate;
	 private LocalDateTime approvedDate;
	 private String adoptState;
	 
	 public static AdoptListResponse AdoptToAdoptList(Adopt adopt) {
		 return AdoptListResponse.builder()
				 .adoptNum(adopt.getAdoptNum())
				 .memberNum(adopt.getMemberNum())
				 .petName(adopt.getPetName())
				 .petImg(adopt.getPetImg())
				 .petAge(adopt.getPetAge())
				 .petSpecies(adopt.getPetSpecies())
				 .sexCd(adopt.getSexCd())
				 .neuterYn(adopt.getNeuterYn())
				 .shelterName(adopt.getShelterName())
				 .shelterTel(adopt.getShelterTel())
				 .shelterAddr(adopt.getShelterAddr())
				 .adoptDate(adopt.getAdoptDate())
				 .approvedDate(adopt.getApprovedDate())
				 .adoptState(adopt.getAdoptState())
				 .build();
	 }
}
