package com.apigateway.ApiGateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.ApiGateway.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtGatewayFilter implements GlobalFilter,Ordered {

	private JwtUtils jwtUtils;	

	public JwtGatewayFilter(JwtUtils jwtUtils) {
		super();
		this.jwtUtils = jwtUtils;
	}


	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String path = exchange.getRequest().getURI().getPath();
		log.info("path = "+path);
		if (path.startsWith("/auth-service/api/auth")) {
            return chain.filter(exchange);
        }
		String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		
		String token = authHeader.substring(7);
		if(!jwtUtils.validateToken(token)) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}
	
	@Override
	public int getOrder() {
		return -1;
	}

}
