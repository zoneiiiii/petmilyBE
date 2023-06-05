package com.petmily.backend.shop.domain;

import java.time.LocalDateTime;

import org.joda.time.DateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orderlist")
public class Orderlist {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderNum;
	
	@Column
	private int memberNum;
	
	@Column
	private int boardNum;

	@Column
	private DateTime orderDate;

	@Column
	private int quantity;

	@Column
	private int cost;

	@Column
	private String orderState;

	@Column
	private int postal;

	@Column
	private String address;

	@Column
	private String addressDetail;

	@Column
	private String note;

	@Column
	private String recipient;

	@Column
	private String recipientTel;

}
