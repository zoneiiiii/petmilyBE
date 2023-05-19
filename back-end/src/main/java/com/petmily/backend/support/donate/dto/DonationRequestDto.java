package com.petmily.backend.support.donate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRequestDto {
    private DonationDto donationDto;
    private PaymentDto paymentDto;
}
