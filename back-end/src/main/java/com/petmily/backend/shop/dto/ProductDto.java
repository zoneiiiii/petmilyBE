package com.petmily.backend.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
private Long boardNum;
	
    private String boardId;
	private String productName;
	private int productCost;
	private int productLike;
	private String productContent;
	private String productImg;
	private int productAmount;
	private String imgThumbnail;
    private Long memberNum;
    private String productCategory;

}
