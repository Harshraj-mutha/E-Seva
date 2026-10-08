package com.app.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.order.dto.ProductResponseDto;

@FeignClient(name = "product-service")
public interface ProductClient {
	
	@GetMapping("/api/product/{id}")
	ProductResponseDto getProductById(@PathVariable("id") int id);

}
