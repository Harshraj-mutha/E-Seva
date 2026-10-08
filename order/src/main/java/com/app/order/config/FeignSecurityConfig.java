package com.app.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;

@Configuration
public class FeignSecurityConfig {
	
	@Bean
	RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("auth value "+ auth);
			if(auth!=null && auth.getCredentials()!=null) {
				String token = auth.getCredentials().toString();
				requestTemplate.header(HttpHeaders.AUTHORIZATION , "Bearer "+token);
				System.out.println(token);
			}
		};
	}

}
