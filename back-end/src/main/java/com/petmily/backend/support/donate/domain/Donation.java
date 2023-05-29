package com.petmily.backend.support.donate.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column
    private Long memberNum;

    @ManyToOne
    @JoinColumn(name="paymentNum")
    private Payment payment;

    public Donation(String donationDonor, String donationName, String donationTel, String donationEmail, Integer donationCost, LocalDateTime donationDate,Long memberNum, Payment payment) {
        this.donationDonor = donationDonor;
        this.donationName = donationName;
        this.donationTel = donationTel;
        this.donationEmail = donationEmail;
        this.donationCost = donationCost;
        this.donationDate = donationDate;
        this.memberNum = memberNum;
        this.payment = payment;
    }

}

