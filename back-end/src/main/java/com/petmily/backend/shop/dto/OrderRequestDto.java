package com.petmily.backend.shop.dto;

import com.petmily.backend.shop.dto.OrderlistDto;
import com.petmily.backend.shop.dto.OrdersDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private OrdersDto ordersDto;
    private PaymentDto paymentDto;
    private List<OrderlistDto> orderlistDtos;
    private List<Long> cartNums;
}
