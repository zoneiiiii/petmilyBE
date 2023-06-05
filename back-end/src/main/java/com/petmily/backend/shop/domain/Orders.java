package com.petmily.backend.shop.domain;

import com.petmily.backend.member.login.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member;

    private LocalDateTime orderDate;

    private String orderState;

    private String address;

    private String addressDetail;

    private String postal;

    private String note;

    private String recipient;

    private String recipientTel;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Orderlist> orderlists = new ArrayList<>();

    public Orders(Member member, LocalDateTime orderDate, String orderState, String address, String addressDetail, String postal, String note, String recipient, String recipientTel) {
        this.member = member;
        this.orderDate = orderDate;
        this.orderState = orderState;
        this.address = address;
        this.addressDetail = addressDetail;
        this.postal = postal;
        this.note = note;
        this.recipient = recipient;
        this.recipientTel = recipientTel;
    }

}
