package com.petmily.backend.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.shop.dto.ProductAddCart;
import com.petmily.backend.shop.dto.ProductDetail;
import com.petmily.backend.shop.dto.ProductList;
import com.petmily.backend.shop.service.ProductService;

@RestController
@RequestMapping("/shop/product")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
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
	public void addCart(@RequestBody ProductAddCart addCart) {
		productService.addCart(addCart);
	}

}
