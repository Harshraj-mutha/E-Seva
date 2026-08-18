package com.app.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.product.dto.ProductRequestDto;
import com.app.product.dto.ProductResponseDto;
import com.app.product.dto.APIResponseDto;
import com.app.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
	
	private final ProductService productService;

	
	@PostMapping
	public ResponseEntity<APIResponseDto> addProduct(@Valid @RequestBody ProductRequestDto product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getProduct(id));
	}
	
	
	
	
	
	

}
