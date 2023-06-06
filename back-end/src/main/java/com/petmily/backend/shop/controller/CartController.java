package com.petmily.backend.shop.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.repository.MemberRepository;
import com.petmily.backend.shop.domain.Cart;
import com.petmily.backend.shop.dto.CartDto;
import com.petmily.backend.shop.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final HttpSession httpSession;

    public CartController(CartService cartService, HttpSession httpSession){
        this.cartService = cartService;
        this.httpSession = httpSession;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getMemberCarts(){
        String memberId = (String)  httpSession.getAttribute("id");
        if(memberId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<CartDto> carts = cartService.getCartsByMemberId(memberId);
        return ResponseEntity.ok(carts);
     }

     @DeleteMapping("/{cartNum}")
     public ResponseEntity<Void> deleteCart(@PathVariable Long cartNum){
         String memberId = (String) httpSession.getAttribute("id");
         if (memberId == null){
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
         cartService.deleteCartByMemberIdAndCartNum(memberId, cartNum);
         return ResponseEntity.ok().build();
     }

    @DeleteMapping
    public ResponseEntity<Void> deleteCarts(@RequestBody List<Long> cartNums) {
        String memberId = (String) httpSession.getAttribute("id");
        if (memberId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        cartService.deleteCartsByMemberIdAndCartNums(memberId, cartNums);
        return ResponseEntity.ok().build();
    }

     @PutMapping("/{cartNum}/increase")
     public ResponseEntity<Void> increaseCartQuantity(@PathVariable Long cartNum){
         String memberId = (String) httpSession.getAttribute("id");
         if (memberId == null){
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
         cartService.increaseCartQuantity(memberId, cartNum);
         return ResponseEntity.ok().build();
     }

    @PutMapping("/{cartNum}/decrease")
    public ResponseEntity<Void> decreaseCartQuantity(@PathVariable Long cartNum){
        String memberId = (String) httpSession.getAttribute("id");
        if (memberId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        cartService.decreaseCartQuantity(memberId, cartNum);
        return ResponseEntity.ok().build();
    }

}
