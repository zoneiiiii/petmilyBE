package com.petmily.backend.mypage.orderlist;

import java.time.LocalDateTime;

import com.petmily.backend.shop.domain.Orderlist;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyOrderlist {
	private Long orderlistNum; // orderlist 기본키
	private Long orderNum; // orders 기본키
	private Long boardNum; // product 기본키
	private String memberId;
	private String imgThumbnail;
	private String productName;
	private int quantity;
	private int cost;
	private String orderState;
	private LocalDateTime orderDate;
	
	public static MyOrderlist OrderlistToMyOrderlist(Orderlist orderlist) {
		return MyOrderlist.builder()
				.orderlistNum(orderlist.getOrderlistNum())
				.orderNum(orderlist.getOrders().getOrderNum())
				.boardNum(orderlist.getProduct().getBoardNum())
				.memberId(orderlist.getOrders().getMember().getMemberId())
				.imgThumbnail(orderlist.getProduct().getImgThumbnail())
				.productName(orderlist.getProduct().getProductName())
				.quantity(orderlist.getQuantity())
				.cost(orderlist.getCost())
				.orderState(orderlist.getOrders().getOrderState())
				.orderDate(orderlist.getOrders().getOrderDate())
				.build();
	}
}
