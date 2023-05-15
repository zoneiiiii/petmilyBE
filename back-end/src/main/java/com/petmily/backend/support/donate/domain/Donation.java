package com.petmily.backend.support.donate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationNum;

    @Column
    private String donationDonor;

    @Column
    private String donationName;

    @Column
    private String donationTel;

    @Column
    private String donationEmail;

    @Column
    private Integer donationCost;

    @Column
    private LocalDateTime donationDate;

    @ManyToOne
    @JoinColumn(name="paymentNum")
    private Payment payment;

    public Donation(String donationDonor, String donationName, String donationTel, String donationEmail, Integer donationCost, LocalDateTime donationDate, Payment payment) {
        this.donationDonor = donationDonor;
        this.donationName = donationName;
        this.donationTel = donationTel;
        this.donationEmail = donationEmail;
        this.donationCost = donationCost;
        this.donationDate = donationDate;
        this.payment = payment;
    }

}

