package com.petmily.backend.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.shop.domain.Cart;
import com.petmily.backend.shop.dto.CartDto;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByMember(Member member);
    void deleteByMemberAndCartNum(Member member, Long cartNum);

    @Transactional
    void deleteByMemberAndCartNumIn(Member member, List<Long> cartNums);
    Cart findByMemberAndCartNum(Member member, Long cartNum);
}
