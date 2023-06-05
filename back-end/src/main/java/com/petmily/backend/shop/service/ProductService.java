package com.petmily.backend.shop.service;

import java.util.List;

import com.petmily.backend.shop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.shop.dto.ProductAddCart;
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
		ProductDetail product = productRepository.findProductDetail(boardNum);
		return product;
	}

	@Transactional // utm 추가-230603
	public Product getProducts(Long boardNum) {
		return productRepository.findProductByBoardNum(boardNum);
	}

	public void addCart(ProductAddCart addCart) {
		long productCheck = productRepository.productCheck(addCart.getMemberId(), addCart.getBoardNum());

		if (productCheck > 0) {
			System.out.println("if");
			productRepository.updateQuantity(addCart.getQuantity(), addCart.getMemberId(), addCart.getBoardNum());
		} else {
			System.out.println("else");
			productRepository.addCart(addCart.getBoardNum(), addCart.getMemberId(), addCart.getProductName(),
					addCart.getProductCost(), addCart.getImgThumbnail(), addCart.getQuantity());
		}
	}
}
