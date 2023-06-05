package com.petmily.backend.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.shop.dto.OrderAdmin;
import com.petmily.backend.shop.dto.OrderStateUpdate;
import com.petmily.backend.shop.repository.OrderlistRepository;

@Service
public class OrderlistService {
	
	@Autowired
	private OrderlistRepository orderlistRepository;
	
	@Transactional
	public List<OrderAdmin> getOrderList() {
		return orderlistRepository.getOrderlist();
		
	}
	
	public void deleteOrder(Long orderNum){
		orderlistRepository.deleteByOrderNum(orderNum);
    }
	
	public void updateOrderState(OrderStateUpdate dto){
		orderlistRepository.updateOrderState(dto.getOrderNum(), dto.getOrderState());
    }

}
