package com.petmily.backend.mypage.orderlist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.shop.domain.Orderlist;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyPageOrderlistService {
	
	private final MyPageOrderlistRepository orderlistRepository;
	
	Page<MyOrderlist> getMyOrderlist(String memberId, int page, String keyword) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("Orders.orderDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Page<Orderlist> orderlist = orderlistRepository.findByOrdersMemberMemberIdAndProductProductNameContaining(memberId, keyword, pageable);
		Page<MyOrderlist> myOrderlist = null;
		if(orderlist != null) myOrderlist = orderlist.map(o -> MyOrderlist.OrderlistToMyOrderlist(o));
		return myOrderlist;
	}
	
	MyOrderDetail getMyOrderDetail(Long orderlistNum) {
		return orderlistRepository.findByOrderlistNum(orderlistNum);
	}

}
