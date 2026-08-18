package com.app.order.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name =  "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
	@Id
	private int orderId;
	
	private int productId;
	
	private int quantity;
	
	private String createUser;
	
	private LocalDate createDtm;
	
}
