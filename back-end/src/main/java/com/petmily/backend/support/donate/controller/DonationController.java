package com.petmily.backend.support.donate.controller;

import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.DonationRequestDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.repository.DonationRepository;
import com.petmily.backend.support.donate.service.DonationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/apply")
    public ResponseEntity<DonationDto> saveDonation(@RequestBody DonationRequestDto donationRequestDto, HttpSession httpSession){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        DonationDto savedDonation = donationService.saveDonation(donationRequestDto.getDonationDto(),donationRequestDto.getPaymentDto(), loggedInUserId);
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

}
