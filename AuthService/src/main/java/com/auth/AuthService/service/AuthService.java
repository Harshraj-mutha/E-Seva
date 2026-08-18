package com.auth.AuthService.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.AuthService.dto.AuthRequestDto;
import com.auth.AuthService.dto.AuthResponseDto;
import com.auth.AuthService.dto.LoginRequestDto;
import com.auth.AuthService.dto.LoginResponseDto;
import com.auth.AuthService.entity.Users;
import com.auth.AuthService.repository.AuthRepository;
import com.auth.AuthService.utils.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
	private AuthRepository authRepo;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;
	private JwtUtils jwtUtil;
	
	public AuthResponseDto addUser(AuthRequestDto authRequestDto) {
		Users user = modelMapper.map(authRequestDto, Users.class);
		user.setCreatedAt(LocalDate.now());
		user.setCreateUser("Harsh");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		authRepo.save(user);
		return new AuthResponseDto("User created Successfully");
	}
	
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		String username = loginRequestDto.getEmail();
		Users user = authRepo.findByEmail(username)
						.orElseThrow(()-> new RuntimeException("Usr not found"));
		String token = jwtUtil.generateToken(user.getEmail());
		return new LoginResponseDto("User login successfully",token, LocalDate.now());
	}

}
