package com.petmily.backend.shop.domain;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.support.donate.domain.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="orderlist")
@Getter
@NoArgsConstructor

public class Orderlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderlistNum;

    @ManyToOne
    @JoinColumn(name = "orderNum", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name="boardNum", nullable = false)
    private Product product;

    private int quantity;

    private int cost;

    public Orderlist(Orders orders, Product product, int quantity, int cost) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }


}
