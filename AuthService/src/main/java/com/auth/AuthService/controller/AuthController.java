package com.auth.AuthService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.AuthService.dto.AuthRequestDto;
import com.auth.AuthService.dto.AuthResponseDto;
import com.auth.AuthService.dto.LoginRequestDto;
import com.auth.AuthService.dto.LoginResponseDto;
import com.auth.AuthService.service.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	private AuthService authService;
	
	@PostMapping
	public ResponseEntity<AuthResponseDto> addUser
						(@Valid @RequestBody AuthRequestDto authRequestDto){
		return  ResponseEntity.status(HttpStatus.CREATED).body(authService.addUser(authRequestDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login( @Valid @RequestBody LoginRequestDto
												loginRequestDao){
		return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequestDao));
	}
	
	

}
