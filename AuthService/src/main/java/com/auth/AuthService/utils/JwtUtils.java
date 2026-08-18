package com.auth.AuthService.utils;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	  private SecretKey getSigningKey() {
	        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	   }
	
	public String generateToken(String email) {
		return Jwts.builder()
			.subject(email)
			.issuedAt(new Date())
			.expiration(Date.from(Instant.now().plusMillis(expiration)))
			.signWith(getSigningKey())
			.compact();
		
	}
	
	public String extraactUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	
	
	private  Claims getClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()	
				.parseSignedClaims(token)
				.getPayload();
		
	}
	

}
