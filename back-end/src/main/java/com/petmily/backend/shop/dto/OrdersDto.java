package com.petmily.backend.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrdersDto {
    private Long orderNum;
    private Long memberNum;
    private LocalDateTime orderDate;
    private String orderState;
    private String address;
    private String addressDetail;
    private String postal;
    private String note;
    private String recipient;
    private String recipientTel;
    private List<OrderlistDto> orderlists;
}
