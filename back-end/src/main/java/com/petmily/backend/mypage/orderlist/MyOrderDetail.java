package com.petmily.backend.mypage.orderlist;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

public interface MyOrderDetail {
	Long getOrderlistNum(); // orderlist 기본키
	Long getOrderNum(); // orders 기본키
	Long getBoardNum(); // product 기본키
	String getMemberId(); // orders.Member
	String getMemberName(); // orders.Member
	String getMemberTel(); // orders.Member
	String getImgThumbnail(); // product 상품이미지
	String getProductName(); // product 상품명
	int getQuantity();// orderlist
	int getCost(); //orderlist쪽 총액
	Integer getTotalCost(); // orders쪽 총액
	int getProductCost(); // product 단가
	String getOrderState(); // orders
	LocalDateTime getOrderDate();// orders
    String getRecipient();// orders
    String getRecipientTel();// orders
    String getPostal();// orders
    String getAddress();// orders
    String getAddressDetail();// orders
    String getNote();// orders
    
    Long getPaymentNum();
    String getMerchantUid();
    String getPaymentState();
    BigDecimal getAmount();
    LocalDateTime getPaymentDate();
}
