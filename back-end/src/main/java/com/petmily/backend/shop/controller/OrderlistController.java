//package com.petmily.backend.shop.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.petmily.backend.shop.dto.OrderAdmin;
//import com.petmily.backend.shop.dto.OrderStateUpdate;
//import com.petmily.backend.shop.service.OrderlistService;
//
//@RestController
//@RequestMapping("/a/order")
//public class OrderlistController {
//
//	private final OrderlistService orderlistService;
//
//	@Autowired
//	public OrderlistController(OrderlistService orderlistService) {
//		this.orderlistService = orderlistService;
//	}
//
//	@GetMapping("/orders")
//	public List<OrderAdmin> getOrderList() {
//		List<OrderAdmin> res = orderlistService.getOrderList();
////		System.out.println(res.size());
//		return orderlistService.getOrderList();
//	}
//
//	@PostMapping("/orders/{orderNum}")
//	public ResponseEntity<String> deleteOrder(@PathVariable("orderNum") Long orderNum) {
//		System.out.println("ordernum:" + orderNum);
//		try {
//			orderlistService.deleteOrder(orderNum);
//			return new ResponseEntity<>("주문 삭제 성공", HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>("주문 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//
//	@PutMapping("/updateState/{orderNum}")
//    private ResponseEntity<String> updateOrderState(@PathVariable Long orderNum, @RequestBody OrderStateUpdate dto){
//        try{
//            dto.setOrderNum(orderNum);
//            orderlistService.updateOrderState(dto);
//            return new ResponseEntity<>("주문 상태 수정 성공", HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>("주문 상태 수정 실패",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
