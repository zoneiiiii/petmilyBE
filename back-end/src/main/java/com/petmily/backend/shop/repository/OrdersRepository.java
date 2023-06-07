package com.petmily.backend.shop.repository;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.shop.domain.Orders;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByMember(Member member);

    @NotNull
    Page<Orders> findAll(@NotNull Pageable pageable);

    long count();

}
