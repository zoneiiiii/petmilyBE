package com.petmily.backend.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petmily.backend.shop.domain.Product;
import com.petmily.backend.shop.dto.ProductDetail;
import com.petmily.backend.shop.dto.ProductList;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery=true, value="select p.boardNum, p.productName, p.productCost, p.imgThumbnail, p.productCategory "
			+ "from product p")
	List<ProductList> getProducts();
	
	@Query(nativeQuery=true, value="select p.boardNum, p.productName, p.productCost, p.imgThumbnail, "
			+ "p.productImg, p.productContent from product p")
	ProductDetail findProductDetial(@Param("boardNum") Long boardNum);
	
}
