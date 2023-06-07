package com.petmily.backend.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.mypage.qna.dto.QnADto;
import com.petmily.backend.shop.domain.Product;
import com.petmily.backend.shop.dto.ProductAddCart;
import com.petmily.backend.shop.dto.ProductDetail;
import com.petmily.backend.shop.dto.ProductDto;
import com.petmily.backend.shop.dto.ProductList;
import com.petmily.backend.shop.service.ProductService;
import com.petmily.backend.support.volunteer.controller.VolunteerController;
import com.petmily.backend.support.volunteer.dto.VolunteerDto;
import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/shop/product")
public class ProductController {
	
	private final ProductService productService;
	private final HttpSession httpSession;
	
	@Autowired
	public ProductController(ProductService productService,HttpSession httpSession) {
		this.productService = productService;
		 this.httpSession = httpSession;
	}
	
	@GetMapping
	public List<ProductList> getProductList() {
		List<ProductList> res = productService.getProductList();
		System.out.println(res.size());
		return productService.getProductList();
	}
	
	@GetMapping("/{boardNum}")
	public ProductDetail getBoard(@PathVariable Long boardNum) {
		return productService.getProduct(boardNum);
	}
	
	@PostMapping("/addCart")
	public ResponseEntity<String> addCart(@RequestBody ProductAddCart addCart) {
		if (addCart.getMemberId() == null || addCart.getMemberId().isEmpty()) {
			return new ResponseEntity<String>("User not logged in", HttpStatus.UNAUTHORIZED);
		}
		productService.addCart(addCart);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/lists") //관리자-전체 상품 리스트
	 public Page<Product> getProducts(@PageableDefault(size=10, sort="boardNum", direction = Sort.Direction.DESC) Pageable pageable){
        return productService.getProducts(pageable);
    }
	
	@PostMapping("/create") //상품 등록
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto prodDto, HttpSession session){
    	String memberId = (String)session.getAttribute("id");
    	ProductDto createProduct = productService.createProduct(prodDto, memberId);
        return ResponseEntity.ok(createProduct);
    }
	
	@PutMapping("/update/{boardNum}") //상품 글 수정
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long boardNum, @RequestBody ProductDto prodDto){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        ProductDto updatedProd = productService.updateProduct(boardNum, prodDto, loggedInUserId);
        log.info("사용자 {} boardNum {} 수정 완료 ", loggedInUserId, boardNum);
        return  ResponseEntity.ok(updatedProd);
    }
}
