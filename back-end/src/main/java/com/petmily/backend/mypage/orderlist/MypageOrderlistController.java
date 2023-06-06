package com.petmily.backend.mypage.orderlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/orderlist")
public class MypageOrderlistController {
	@Autowired
	private final MyPageOrderlistService myPageOrderlistService;
	
	
}
