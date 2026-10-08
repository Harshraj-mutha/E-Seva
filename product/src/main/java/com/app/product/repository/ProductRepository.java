package com.app.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	 Optional<Product> findByproductName(String name);

}
