package com.petmily.backend.shop.dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class OrderlistDto {
    private Long orderlistNum;
    private Long orderNum;
    private Long boardNum;
    private int quantity;
    private int cost;
    private String productName;
}
