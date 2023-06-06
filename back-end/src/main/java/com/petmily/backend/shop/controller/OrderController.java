package com.petmily.backend.shop.controller;

import com.petmily.backend.shop.dto.OrderRequestDto;
import com.petmily.backend.shop.dto.OrderlistDto;
import com.petmily.backend.shop.dto.OrdersDto;
import com.petmily.backend.shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<OrdersDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/allOrdersASC")
    public ResponseEntity<List<OrdersDto>> getAllOrdersASC() {
        List<OrdersDto> orders = orderService.getAllOrdersASC();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/allOrdersDESC")
    public ResponseEntity<List<OrdersDto>> getAllOrdersDESC() {
        List<OrdersDto> orders = orderService.getAllOrdersDESC();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/pagedOrders")
    public ResponseEntity<Page<OrdersDto>> getPagedOrders(Pageable pageable) {
        Page<OrdersDto> orders = orderService.getOrders(pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("orders/count")
    public ResponseEntity<Long> getTotalOrders(){
        return ResponseEntity.ok(orderService.getTotalOrders());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrdersDto>> getAllOrders(HttpSession httpSession){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        List<OrdersDto> orders = orderService.getAllOrders(loggedInUserId);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/allOrderlists")
    public ResponseEntity<List<OrderlistDto>> getAllOrderlists(){
        List<OrderlistDto> orderlists = orderService.getAllOrderlist();
        return new ResponseEntity<>(orderlists, HttpStatus.OK);
    }

    @GetMapping("/orderlists/{orderNum}")
    public ResponseEntity<List<OrderlistDto>> getOrderlists(@PathVariable Long orderNum){
        List<OrderlistDto> orderlists = orderService.getOrderlistByOrderNum(orderNum);

        return new ResponseEntity<>(orderlists, HttpStatus.OK);
    }

    @GetMapping("/allOrdersCost")
    public ResponseEntity<Double> calculateTotalCost(){
        double totalCost = orderService.calculateTotalCost();
        return new ResponseEntity<>(totalCost, HttpStatus.OK);
    }

    @PutMapping("/orderState/{orderNum}")
    public ResponseEntity<Void> updateOrderState(@PathVariable Long orderNum, @RequestParam String orderState) {
        orderService.updateOrderState(orderNum, orderState);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
