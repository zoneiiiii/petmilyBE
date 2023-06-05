package com.petmily.backend.shop.dto;

import lombok.Data;

@Data
public class OrderStateUpdate {
	private Long orderNum;
	private String orderState;
}
