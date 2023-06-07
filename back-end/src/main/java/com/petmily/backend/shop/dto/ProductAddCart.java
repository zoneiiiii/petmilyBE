package com.petmily.backend.shop.dto;

import lombok.Data;

@Data
public class ProductAddCart {
	
	private int boardNum;
	private String memberId;
	private String productName;
	private int productCost;
	private int quantity;

}
