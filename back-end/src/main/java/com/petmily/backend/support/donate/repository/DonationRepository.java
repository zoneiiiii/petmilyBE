package com.petmily.backend.support.donate.repository;

import com.petmily.backend.support.donate.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {
}
