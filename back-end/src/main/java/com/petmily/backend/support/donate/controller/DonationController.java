package com.petmily.backend.support.donate.controller;

import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.DonationRequestDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.repository.DonationRepository;
import com.petmily.backend.support.donate.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donate")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    public ResponseEntity<List<DonationDto>> getAllDonations(){
        List<DonationDto> donations = donationService.getAllDonations();
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<DonationDto> saveDonation(@RequestBody DonationRequestDto donationRequestDto){
        DonationDto savedDonation = donationService.saveDonation(donationRequestDto.getDonationDto(),donationRequestDto.getPaymentDto());
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

}
