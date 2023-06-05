package com.petmily.backend.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.shop.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.shop.dto.CartDto;
import com.petmily.backend.shop.repository.CartRepository;
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final MemberService memberService;

    public CartService(CartRepository cartRepository, MemberService memberService) {
        this.cartRepository = cartRepository;
        this.memberService = memberService;
    }

    public List<CartDto> getCartsByMemberId(String memberId) {
        Member member = memberService.getMember(memberId);
        List<Cart> carts = cartRepository.findByMember(member);

        List<CartDto> cartDtos = new ArrayList<>();
        for(Cart cart : carts) {
            CartDto cartDto = new CartDto();
            cartDto.setCartNum(cart.getCartNum());
            cartDto.setMemberNum(cart.getMember().getMemberNum());
            cartDto.setBoardNum(cart.getProduct().getBoardNum());
            cartDto.setProductName(cart.getProduct().getProductName());
            cartDto.setThumbnailImg(cart.getProduct().getImgThumbnail());
            cartDto.setQuantity(cart.getQuantity());
            cartDto.setProductCost(cart.getProductCost());
            cartDtos.add(cartDto);
        }
        return cartDtos;
    }

    public void deleteCartByMemberIdAndCartNum(String memberId, Long cartNum){
        Member member = memberService.getMember(memberId);
        cartRepository.deleteByMemberAndCartNum(member, cartNum);
    }

    public void deleteCartsByMemberIdAndCartNums(String memberId, List<Long> cartNums){
        Member member = memberService.getMember(memberId);
        cartRepository.deleteByMemberAndCartNumIn(member, cartNums);
    }

    public void increaseCartQuantity(String memberId, Long cartNum){
        Member member = memberService.getMember(memberId);
        Cart cart = cartRepository.findByMemberAndCartNum(member, cartNum);
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
        }
    }

    public void decreaseCartQuantity(String memberId, Long cartNum) {
        Member member = memberService.getMember(memberId);
        Cart cart = cartRepository.findByMemberAndCartNum(member, cartNum);
        if (cart != null && cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
            cartRepository.save(cart);
        }
    }

}


