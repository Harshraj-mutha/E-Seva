package com.auth.AuthService.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.AuthService.service.CustomUserDetailService;
import com.auth.AuthService.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtUtils jwtUtil;
	private CustomUserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("In JwtAuthenticationFilter");

		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authHeader.substring(7);
		String userName = "";
		try {
			userName = jwtUtil.extraactUsername(token);
		} catch (Exception e) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(!userName.equals("") && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userDetailService.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		System.out.println("end JwtAuthenticationFilter");
		filterChain.doFilter(request, response);
			
	}

}
