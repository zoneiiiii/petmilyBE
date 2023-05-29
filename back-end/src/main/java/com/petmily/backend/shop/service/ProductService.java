package com.petmily.backend.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.shop.dto.ProductDetail;
import com.petmily.backend.shop.dto.ProductDto;
import com.petmily.backend.shop.dto.ProductList;
import com.petmily.backend.shop.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public List<ProductList> getProductList() {
		return productRepository.getProducts();
	}
	
	@Transactional
	public ProductDetail getProduct(Long boardNum) {
		ProductDetail product = productRepository.findProductDetial(boardNum);
		return product;
	}
}
