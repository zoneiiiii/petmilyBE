package com.petmily.backend.support.donate.controller;

import com.petmily.backend.support.donate.dto.DonationDto;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("donate/apply")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationDto> saveDonation(@RequestBody DonationDto donationDto, @RequestBody PaymentDto paymentDto){
        DonationDto savedDonation = donationService.saveDonation(donationDto, paymentDto);
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

}
