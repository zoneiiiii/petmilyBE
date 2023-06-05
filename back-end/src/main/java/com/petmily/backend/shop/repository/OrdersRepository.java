package com.petmily.backend.shop.repository;

import com.petmily.backend.shop.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
