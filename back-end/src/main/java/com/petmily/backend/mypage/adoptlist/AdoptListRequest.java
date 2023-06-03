package com.petmily.backend.mypage.adoptlist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptListRequest {
	@NotNull
	private Long petNum;
	@NotEmpty
	private String PetName;
	private String petImg;
}

