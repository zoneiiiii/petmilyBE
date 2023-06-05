package com.petmily.backend.mypage.Info;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NavInfo {
	private Long memberNum;
	private String memberNickname;
	private String memberImg;
}
