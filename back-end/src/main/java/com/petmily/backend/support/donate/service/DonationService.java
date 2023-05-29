package com.petmily.backend.support.donate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.support.donate.domain.Donation;
import com.petmily.backend.support.donate.domain.Payment;
import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.repository.DonationRepository;
import com.petmily.backend.support.donate.repository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;

@Service
public class DonationService {

    @Autowired
    private IamportClient iamportClient;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<DonationDto> getAllDonations(){
        List<Donation> donations = donationRepository.findAll();
        List<DonationDto> donationDtos = new ArrayList<>();

        for (Donation donation : donations){
            DonationDto donationDto = new DonationDto();
            donationDto.setDonationNum(donation.getDonationNum());
            donationDto.setDonationDonor(donation.getDonationDonor());
            donationDto.setDonationName(donation.getDonationName());
            donationDto.setDonationTel(donation.getDonationTel());
            donationDto.setDonationEmail(donation.getDonationEmail());
            donationDto.setDonationCost(donation.getDonationCost());
            donationDto.setDonationDate(donation.getDonationDate());
            donationDto.setPaymentNum(donation.getPayment().getPaymentNum());

            donationDtos.add(donationDto);
        }
        return donationDtos;
    }


    @Transactional //기부 저장
    public DonationDto saveDonation(DonationDto donationDto, PaymentDto paymentDto){
        Payment payment = new Payment(
                paymentDto.getOrderNum(),
                paymentDto.getMerchantUid(),
                paymentDto.getImpUid(),
                paymentDto.getPaymentState(),
                paymentDto.getAmount(),
                paymentDto.getPaymentDate()
        );

        // 아임포트 결제 검증
        IamportResponse<com.siot.IamportRestClient.response.Payment> paymentResponse;
        try {
            paymentResponse = iamportClient.paymentByImpUid(paymentDto.getImpUid());

            if (paymentResponse == null
                    || !paymentResponse.getResponse().getAmount().equals(paymentDto.getAmount())) {
                // import Uid와 Client Uid 비교 아임포트쪽 금액과 클라이언트 입력 금액 다를때, ImpUid가 Null일때 결제 검증 실패
                throw new RuntimeException("결제 검증 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("결제 검증 도중 오류 발생!");
        }

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
