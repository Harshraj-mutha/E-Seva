package com.apigateway.ApiGateway.utils;

import java.nio.charset.StandardCharsets;
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
	private String expirationTime;
	
	public SecretKey getKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public boolean validateToken(String token) {
		try {
			Claims claim = getClaims(token);
			return claim.getExpiration().after(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser()
		.verifyWith(getKey())
		.build()
		.parseSignedClaims(token)
		.getPayload();
	}

}
