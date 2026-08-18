package com.app.product.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class productConfig {
	
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

}
