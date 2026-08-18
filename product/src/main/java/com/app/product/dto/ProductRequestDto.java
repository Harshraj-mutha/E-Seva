package com.app.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDto {
	

	@NotBlank(message =  "product name can not be empty")
	private String productName;
	
	@NotNull(message =  "product quantity cannot be blank")
	@Min(value = 1, message = "Product should be greater than 0")
	private int quantity;
	

}
