package com.app.product.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.product.dto.APIResponseDto;
import com.app.product.dto.ProductRequestDto;
import com.app.product.dto.ProductResponseDto;
import com.app.product.entity.Product;
import com.app.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepo;
	private final ModelMapper modelMapper;
	


	public APIResponseDto addProduct(ProductRequestDto product) {
		Product newProduct = modelMapper.map(product, Product.class);
		newProduct.setCreatedAt(LocalDate.now());
		newProduct.setCreateUser("Admin");
		Product AddedProduct = productRepo.save(newProduct);
		return new APIResponseDto("Product added successfully with id : " + AddedProduct.getId());
	}
	
	public ProductResponseDto getProduct(int id) {
		Product product = productRepo.getReferenceById(id);
		return modelMapper.map(product,ProductResponseDto.class);		
		
	}
	
	 

}
