package com.petmily.backend.mypage.orderlist;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petmily.backend.shop.domain.Orderlist;

public interface MyPageOrderlistRepository extends JpaRepository<Orderlist, Long> {

}
