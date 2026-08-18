package com.app.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {

	private int productId;
	
	private int quantity;
}
