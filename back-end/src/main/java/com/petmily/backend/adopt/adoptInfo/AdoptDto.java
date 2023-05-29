package com.petmily.backend.adopt.adoptInfo;


import java.time.LocalDateTime;

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

}
