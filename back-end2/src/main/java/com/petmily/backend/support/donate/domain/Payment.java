package com.petmily.backend.support.donate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNum;

    @Column
    private Long orderNum;

    @Column(nullable = false)
    private String merchantUid;

    @Column(nullable = false)
    private String impUid;

    @Column
    private String paymentState;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    public Payment(Long orderNum, String merchantUid, String impUid, String paymentState, BigDecimal amount, LocalDateTime paymentDate) {
        this.orderNum = orderNum;
        this.merchantUid = merchantUid;
        this.impUid = impUid;
        this.paymentState = paymentState;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
