package com.petmily.backend.support.donate.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private Long paymentNum;
    private Long orderNum;
    private String merchantUid;
    private String impUid;
    private String paymentState;
    private BigDecimal amount;
    private LocalDateTime paymentDate;

}
