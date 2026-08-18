package com.app.order.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.order.util.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("in orders jwt filter");
		String authHeader = request.getHeader("Authorization");
		if(authHeader ==null || !authHeader.startsWith("Bearer ")) {
			System.out.println("no token found");
			return;
		}
		System.out.println("token found");
		String token = authHeader.substring(7);
		
		if(!jwtUtils.validateToken(token)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		Claims claims = jwtUtils.getClaims(token);
        String username = claims.getSubject(); 
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("calling next filter");
		 filterChain.doFilter(request, response);
		
	}

}
