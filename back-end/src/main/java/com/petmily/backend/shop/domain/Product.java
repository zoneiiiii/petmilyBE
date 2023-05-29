package com.petmily.backend.shop.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;
	
	@Column
    private String boardId;
	
	@Column
	private String productName;
	
	@Column
	private String productCost;
	
	@Column
	private int productLike;
	
	@Column
	private String productContent;
	
	@Column
	private String productImg;
	
	@Column
	private String productAmount;
	
	@Column
	private String imgThumbnail;
	
	@Column
    private Long memberNum;
	
	@Column
	private String productCategory;
	
}
