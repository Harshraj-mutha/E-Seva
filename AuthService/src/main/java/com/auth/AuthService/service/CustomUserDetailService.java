package com.auth.AuthService.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.AuthService.entity.Users;
import com.auth.AuthService.repository.AuthRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	
	private AuthRepository authRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user =authRepo.findByEmail(username).orElseThrow( 
				() -> new RuntimeException ("User not found"));
		
		 return org.springframework.security.core.userdetails.User
	                .withUsername(user.getEmail())
	                .password(user.getPassword()) 
	                .authorities(Collections.emptyList()) 
	                .build();
		
	}

}
