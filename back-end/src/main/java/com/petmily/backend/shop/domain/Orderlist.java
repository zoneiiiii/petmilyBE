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
	
// 	@Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private long orderNum;
	
// 	@Column
// 	private int memberNum;
	
// 	@Column
// 	private int boardNum;

// 	@Column
// 	private DateTime orderDate;

// 	@Column
// 	private int quantity;

// 	@Column
// 	private int cost;

// 	@Column
// 	private String orderState;

// 	@Column
// 	private int postal;

// 	@Column
// 	private String address;

// 	@Column
// 	private String addressDetail;

// 	@Column
// 	private String note;

// 	@Column
// 	private String recipient;

// 	@Column
// 	private String recipientTel;

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
