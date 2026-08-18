package com.auth.AuthService.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
	
	private String msg;
	
	private String token;
	
	private LocalDate createdAt;
	

}
