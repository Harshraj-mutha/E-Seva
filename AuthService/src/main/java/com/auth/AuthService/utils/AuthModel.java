package com.auth.AuthService.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthModel {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
