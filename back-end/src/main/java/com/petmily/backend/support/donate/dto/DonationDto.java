package com.petmily.backend.support.donate.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DonationDto {
    private Long donationNum;
    private String donationDonor;
    private String donationName;
    private String donationTel;
    private String donationEmail;
    private Integer donationCost;
    private LocalDateTime donationDate;
    private Long paymentNum;

}
