package com.app.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.order.dto.OrderRequestDto;
import com.app.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
	
	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<String> getAllProductDetails( @Valid @RequestBody OrderRequestDto orderDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.PlacedOrder(orderDto));
	}
}
