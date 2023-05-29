package com.petmily.backend.support.donate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmily.backend.support.donate.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
