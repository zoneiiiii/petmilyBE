package com.petmily.backend.shop.dto;

public interface ProductDetail {
	
	Long getBoardNum();
	String getProductName();
	int getProductCost();
	String getImgThumbnail();
	String getProductImg();
	String getProductContent();
	int getProductAmount();
	String getProductCategory();
}
