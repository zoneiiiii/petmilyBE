package com.petmily.backend.shop.domain;

import com.petmily.backend.member.login.domain.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cartNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberNum")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardNum")
	private Product product;
	
	private int quantity;
	
	private int productCost;

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
