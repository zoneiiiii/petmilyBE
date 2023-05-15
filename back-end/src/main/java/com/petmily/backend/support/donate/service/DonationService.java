package com.petmily.backend.support.donate.service;

import com.petmily.backend.support.donate.domain.Donation;
import com.petmily.backend.support.donate.domain.Payment;
import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.repository.DonationRepository;
import com.petmily.backend.support.donate.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public DonationDto saveDonation(DonationDto donationDto, PaymentDto paymentDto){
        Payment payment = new Payment(
                paymentDto.getOrderNum(),
                paymentDto.getMerchantUid(),
                paymentDto.getTid(),
                paymentDto.getPaymentState(),
                paymentDto.getAmount(),
                paymentDto.getPaymentDate()
        );
        Payment savedPayment = paymentRepository.save(payment);

        Donation donation = new Donation(
                donationDto.getDonationDonor(),
                donationDto.getDonationName(),
                donationDto.getDonationTel(),
                donationDto.getDonationEmail(),
                donationDto.getDonationCost(),
                donationDto.getDonationDate(),
                savedPayment
        );

        Donation savedDonation = donationRepository.save(donation);
        donationDto.setDonationNum(savedDonation.getDonationNum());
        donationDto.setPaymentNum(savedPayment.getPaymentNum());
        return donationDto;

    }

}
