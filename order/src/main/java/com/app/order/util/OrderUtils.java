package com.app.order.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class OrderUtils {

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
