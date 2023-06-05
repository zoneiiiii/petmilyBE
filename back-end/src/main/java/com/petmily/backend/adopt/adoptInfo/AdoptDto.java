package com.petmily.backend.adopt.adoptInfo;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdoptDto {
	 private Long adoptNum;
	 private Long memberNum;
	 private String petName;
	 private String petImg;
	 private String adopterName;
	 private String adopterBirth;
	 private String adopterTel;
	 private String adopterAddr;
	 private String adopterEmail;
	 private LocalDateTime adoptDate;
	 private String adoptState;
	 private String petAge;
	 private String petSpecies;
	 private String shelterName;
	 private String shelterTel;
	 private String shelterAddr;
	 private LocalDateTime approvedDate;
	 private String sexCd;
	 private String neuterYn;

}
