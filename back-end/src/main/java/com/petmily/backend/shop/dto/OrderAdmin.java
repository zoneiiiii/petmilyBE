package com.petmily.backend.shop.dto;

import java.time.LocalDateTime;

public interface OrderAdmin {
	
	Long getOrderNum();
	LocalDateTime getOrderDate();
	String getOrderState();
	int getBoardNum();
	int getQuantity();
	int getMemberNum();
	int getCost();

}
