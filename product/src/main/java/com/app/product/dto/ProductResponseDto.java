package com.app.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDto {

	private String productName;
	
	private int quantity;
}
