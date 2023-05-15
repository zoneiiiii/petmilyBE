package com.petmily.backend.support.donate.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto {
    private Long paymentNum;
    private Long orderNum;
    private String merchantUid;
    private String tid;
    private String paymentState;
    private BigDecimal amount;
    private LocalDateTime paymentDate;

}
