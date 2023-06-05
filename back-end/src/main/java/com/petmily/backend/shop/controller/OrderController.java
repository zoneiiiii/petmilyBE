package com.petmily.backend.shop.controller;

import com.petmily.backend.shop.dto.OrderRequestDto;
import com.petmily.backend.shop.dto.OrdersDto;
import com.petmily.backend.shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<OrdersDto> saveOrder(@RequestBody OrderRequestDto orderRequestDto, HttpSession httpSession){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        OrdersDto savedOrder = orderService.saveOrder(
                orderRequestDto.getOrdersDto(),
                orderRequestDto.getPaymentDto(),
                orderRequestDto.getOrderlistDtos(),
                orderRequestDto.getCartNums(),
                loggedInUserId );
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }



}
