package com.petmily.backend.mypage.orderlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/orderlist")
public class MypageOrderlistController {
	@Autowired
	private final MyPageOrderlistService myPageOrderlistService;
	@Autowired
	private final HttpSession httpSession;
	
	@GetMapping("/list")
	public Page<MyOrderlist> getMyList(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="search", defaultValue="") String search) {
		String id = (String) httpSession.getAttribute("id");
		if(id == null) return null;
		return myPageOrderlistService.getMyOrderlist(id, page, search);
	}
	
	@GetMapping("/detail/{orderlistNum}")
	public MyOrderDetail getMyOrderlistDetail(@PathVariable(value="orderlistNum") Long orderlistNum) {
		return myPageOrderlistService.getMyOrderDetail(orderlistNum);
	}
}
