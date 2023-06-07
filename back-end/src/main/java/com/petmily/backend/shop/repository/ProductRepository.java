package com.petmily.backend.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.shop.domain.Product;
import com.petmily.backend.shop.dto.ProductDetail;
import com.petmily.backend.shop.dto.ProductList;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery=true, value="select p.boardNum, p.productName, p.productCost, p.imgThumbnail, p.productCategory "
			+ "from product p order by p.boardNum desc")
	List<ProductList> getProducts();
	
	@Query(nativeQuery=true, value="select p.boardNum, p.productName, p.productCost, p.imgThumbnail, "
			+ "p.productImg, p.productContent, p.productCategory, p.productAmount from product p where p.boardNum = :boardNum")
	ProductDetail findProductDetail(@Param("boardNum") Long boardNum);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true, value="insert into cart (boardNum, memberNum, productName, productCost, quantity)"
			+ "values (:boardNum, (select memberNum from member where memberId = :memberId), :productName, :productCost, :quantity)")
	void addCart(@Param("boardNum") int boardNum, @Param("memberId") String memberId, @Param("productName") String productName, @Param("productCost") int productCost,
			@Param("quantity") int quantity);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true, value="update cart set quantity = quantity + :quantity where memberNum = (select memberNum from member where memberId = :memberId) and boardNum = :boardNum")
	void updateQuantity(@Param("quantity") int quantity, @Param("memberId") String memberId, @Param("boardNum") int boardNum);
	
	@Query(nativeQuery=true, value="select count(*) from cart c where c.memberNum = (select memberNum from member where memberId = :memberId) and c.boardNum = :boardNum")
	long productCheck(@Param("memberId") String memberId, @Param("boardNum") int boardNum);

	Product findProductByBoardNum(Long boardNum); // utm 추가-230604
	
}
