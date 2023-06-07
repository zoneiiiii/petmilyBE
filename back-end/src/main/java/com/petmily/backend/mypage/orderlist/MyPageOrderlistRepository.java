package com.petmily.backend.mypage.orderlist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petmily.backend.shop.domain.Orderlist;

public interface MyPageOrderlistRepository extends JpaRepository<Orderlist, Long> {

	
	Page<Orderlist> findByOrdersMemberMemberIdAndProductProductNameContaining(@Param("memberId") String memberId, @Param("keyword") String keyword, Pageable pageable);
	
	@Query(value="SELECT * "
			+ "FROM member m "
			+ "INNER JOIN orders os ON m.memberNum = os.memberNum "
			+ "INNER JOIN orderlist ol ON ol.orderNum = os.orderNum "
			+ "INNER JOIN product p ON p.boardNum = ol.boardNum "
			+ "INNER JOIN payment py ON py.orderNum = ol.orderNum "
			+ "where ol.orderlistNum=:orderlistNum", nativeQuery=true)
	MyOrderDetail findByOrderlistNum(Long orderlistNum);
}
