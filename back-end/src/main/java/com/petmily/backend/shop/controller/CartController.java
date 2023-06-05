//package com.petmily.backend.shop.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.petmily.backend.member.login.domain.Member;
//import com.petmily.backend.member.login.repository.MemberRepository;
//import com.petmily.backend.shop.domain.Cart;
//import com.petmily.backend.shop.dto.CartDto;
//import com.petmily.backend.shop.service.CartService;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//	 private final CartService cartService;
//	 private final MemberRepository memberRepository;
//	    @Autowired
//	    public CartController(CartService cartService,MemberRepository memberRepository) {
//	        this.cartService = cartService;
//	        this.memberRepository = memberRepository;
//	    }
//	    
//	    @GetMapping("/{memberNum}")
//	    public ResponseEntity<List<CartDto>> getCartItems(@PathVariable Long memberNum) {
//	        Member member = memberRepository.findByMemberNum(memberNum);
//
//	        if (member == null) {
//	            return ResponseEntity.badRequest().body(null);
//	        }
//
//	        List<CartDto> cartItems = cartService.getCartItems(member);
//	        return ResponseEntity.ok(cartItems);
//	    }
//}