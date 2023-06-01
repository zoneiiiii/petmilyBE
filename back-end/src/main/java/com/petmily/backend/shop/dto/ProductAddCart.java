package com.petmily.backend.shop.dto;

import lombok.Data;

@Data
public class ProductAddCart {
	
	private String productName;
	private String productCost;
	private String imgThumbnail;
	private int quantity;

}
