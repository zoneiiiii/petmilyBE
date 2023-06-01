package com.petmily.backend.support.donate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.support.donate.domain.Donation;
import com.petmily.backend.support.donate.domain.Payment;
import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.repository.DonationRepository;
import com.petmily.backend.support.donate.repository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

    private final IamportClient iamportClient;
    private final DonationRepository donationRepository;
    private final PaymentRepository paymentRepository;
    private final MemberService memberService;

    public DonationService(IamportClient iamportClient, DonationRepository donationRepository, PaymentRepository paymentRepository, MemberService memberService){
        this.iamportClient = iamportClient;
        this.donationRepository = donationRepository;
        this.paymentRepository = paymentRepository;
        this.memberService = memberService;
    }

    private List<DonationDto> getDonations(Sort sort){
        List<Donation> donations = donationRepository.findAll(sort);
        List<DonationDto> donationDtos = new ArrayList<>();

        for (Donation donation : donations){
            donationDtos.add(convertToDto(donation));
        }
        return donationDtos;
    }

    public List<DonationDto> getAllDonations(){
        return getDonations(Sort.by(Sort.Direction.DESC, "donationDate"));
    }

    public List<DonationDto> getAllDonationsASC(){
        return getDonations(Sort.by(Sort.Direction.ASC, "donationDate"));
    }

    public Page<DonationDto> getAllMemberDonation(Pageable pageable){
        Page<Donation> donations = donationRepository.findByMemberNumIsNotNull(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("donationDate").descending())
        );

        List<DonationDto> donationDtos = donations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(donationDtos, pageable, donations.getTotalElements());
    }

    public Page<DonationDto> getAllNonMemberDonation(Pageable pageable){
        Page<Donation> donations = donationRepository.findByMemberNumIsNull(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("donationDate").descending())
        );

        List<DonationDto> donationDtos = donations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(donationDtos, pageable, donations.getTotalElements());
    }

    public Long getTotalDonation() {
        Long total = donationRepository.sumDonationCost();
        return total == null ? 0L : total;
    }

    @Transactional //기부 저장
    public DonationDto saveDonation(DonationDto donationDto, PaymentDto paymentDto, String loggedInUserId){
        Payment payment = new Payment(
                paymentDto.getOrderNum(),
                paymentDto.getMerchantUid(),
                paymentDto.getImpUid(),
                paymentDto.getPaymentState(),
                paymentDto.getAmount(),
                paymentDto.getPaymentDate()
        );

        Long loggedInUserNum = null;
        if(loggedInUserId != null){
            Member member = memberService.getMember(loggedInUserId);
            loggedInUserNum = member.getMemberNum();
        }

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
                loggedInUserNum,
                savedPayment
        );

        Donation savedDonation = donationRepository.save(donation);
        donationDto.setDonationNum(savedDonation.getDonationNum());
        donationDto.setPaymentNum(savedPayment.getPaymentNum());
        donationDto.setMemberNum(loggedInUserNum);
        return donationDto;

    }

    private DonationDto convertToDto(Donation donation) {
        DonationDto donationDto = new DonationDto();
        donationDto.setDonationNum(donation.getDonationNum());
        donationDto.setDonationDonor(donation.getDonationDonor());
        donationDto.setDonationName(donation.getDonationName());
        donationDto.setDonationTel(donation.getDonationTel());
        donationDto.setDonationEmail(donation.getDonationEmail());
        donationDto.setDonationCost(donation.getDonationCost());
        donationDto.setDonationDate(donation.getDonationDate());
        donationDto.setMemberNum(donation.getMemberNum());
        if (donation.getPayment() != null) {
            donationDto.setPaymentNum(donation.getPayment().getPaymentNum());
        }
        return donationDto;
    }

}
