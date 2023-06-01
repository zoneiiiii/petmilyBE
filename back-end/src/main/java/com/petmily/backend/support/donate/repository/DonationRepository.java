package com.petmily.backend.support.donate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petmily.backend.support.donate.domain.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {
    Page<Donation> findByMemberNumIsNotNull(Pageable pageable); //회원 기부내역
    Page<Donation> findByMemberNumIsNull(Pageable pageable); // 비회원 기부내역

    @Query("SELECT SUM(donationCost) FROM Donation")
    Long sumDonationCost(); //총 기부금
}
