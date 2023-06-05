package com.petmily.backend.shop.repository;

import com.petmily.backend.shop.domain.Orderlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderlistRepository extends JpaRepository<Orderlist, Long> {

// @Repository
// public interface OrderlistRepository extends JpaRepository<Orderlist, Long> {
// 	@Query(nativeQuery=true, value="select o.orderNum, o.orderDate, o.orderState, o.boardNum, o.quantity, "
// 			+ "o.memberNum, o.cost from orderlist o order by o.orderDate desc")
// 	List<OrderAdmin> getOrderlist();
	
// 	@Modifying
// 	@Transactional
// 	@Query(nativeQuery=true, value="delete from orderlist where orderNum = :orderNum")
// 	void deleteByOrderNum(Long orderNum);
	
// 	@Modifying
// 	@Transactional
// 	@Query(nativeQuery=true, value="update orderlist o set o.orderState = :orderState where o.orderNum = :orderNum")
// 	void updateOrderState(@Param("orderNum") Long orderNum, @Param("orderState") String orderState);

}
