package com.apigateway.ApiGateway.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CorrelationIdUtils {

	public static final String CORRELATION_ID = "E-Correlation-Id";
	
	public String generateId() {
		return UUID.randomUUID().toString();
	}
}
