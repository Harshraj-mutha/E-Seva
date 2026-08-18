package com.app.product.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.product.filter.JwtauthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class productConfig {
	
	private JwtauthenticationFilter jwtAuthFilter;
	
	@Bean
	 ModelMapper getMapper() {
		return new ModelMapper();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http ) {
		return http.
			csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(req -> req.anyRequest().authenticated())
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}

}
