//package com.petmily.backend.shop.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.petmily.backend.member.login.domain.Member;
//import com.petmily.backend.shop.dto.CartDto;
//import com.petmily.backend.shop.repository.CartRepository;
//@Service
//public class CartService {
//	private final CartRepository cartRepository;
//
//    @Autowired
//    public CartService(CartRepository cartRepository) {
//        this.cartRepository = cartRepository;
//    }
//    
//    public List<CartDto> getCartItems(Member member) {
//        return cartRepository.findByMember(member);
//    }
//    
//    
//
//}
