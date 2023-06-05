package com.petmily.backend.shop.repository;

import com.petmily.backend.shop.domain.Orderlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlistRepository extends JpaRepository<Orderlist, Long> {
}
