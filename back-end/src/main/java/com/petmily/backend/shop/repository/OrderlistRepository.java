package com.petmily.backend.shop.repository;

import com.petmily.backend.shop.domain.Orderlist;
import com.petmily.backend.shop.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderlistRepository extends JpaRepository<Orderlist, Long> {

    List<Orderlist> findByOrders(Orders orders);

}
