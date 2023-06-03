package com.petmily.backend.support.donate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.DonationRequestDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.repository.DonationRepository;
import com.petmily.backend.support.donate.service.DonationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.DonationRequestDto;
import com.petmily.backend.support.donate.service.DonationService;

@RestController
@RequestMapping("/donate")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService){
        this.donationService = donationService;
    }
    @GetMapping
    public ResponseEntity<List<DonationDto>> getAllDonations(){
        List<DonationDto> donations = donationService.getAllDonations();
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @GetMapping("/Asc")
    public ResponseEntity<List<DonationDto>> getAllDonationsASC(){
        List<DonationDto> donations = donationService.getAllDonationsASC();
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @GetMapping("/member")
    public ResponseEntity<Page<DonationDto>> getAllMemberDonations(Pageable pageable) {
        Page<DonationDto> donations = donationService.getAllMemberDonation(pageable);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @GetMapping("/non-member")
    public ResponseEntity<Page<DonationDto>> getAllNonMemberDonations(Pageable pageable) {
        Page<DonationDto> donations = donationService.getAllNonMemberDonation(pageable);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalDonation() {
        Long total = donationService.getTotalDonation();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/total/member")
    public ResponseEntity<Long> getMemberTotalDonation() {
        Long total = donationService.getMemberTotalDonation();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/total/non-member")
    public ResponseEntity<Long> getNonMemberTotalDonation() {
        Long total = donationService.getNonMemberTotalDonation();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<DonationDto> saveDonation(@RequestBody DonationRequestDto donationRequestDto, HttpSession httpSession){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        DonationDto savedDonation = donationService.saveDonation(donationRequestDto.getDonationDto(),donationRequestDto.getPaymentDto(), loggedInUserId);
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

}
