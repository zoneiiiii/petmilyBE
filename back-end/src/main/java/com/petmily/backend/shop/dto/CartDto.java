package com.petmily.backend.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
	private Long cartNum;
	private Long memberNum;
	private Long boardNum;
	private String productName;
	private String ThumbnailImg;
	private int quantity;
	private int productCost;
}
