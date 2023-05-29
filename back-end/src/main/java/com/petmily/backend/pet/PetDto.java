package com.petmily.backend.pet;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PetDto {
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

}
