package com.apigateway.ApiGateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.ApiGateway.utils.JwtUtils;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Component
public class JwtGatewayFilter implements GlobalFilter,Ordered {

	private JwtUtils jwtUtils;	

	public JwtGatewayFilter(JwtUtils jwtUtils) {
		super();
		this.jwtUtils = jwtUtils;
	}


	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("In JwtGatewayFilter method");
		String path = exchange.getRequest().getURI().getPath();
		System.out.println(path);
		if (path.startsWith("/auth-service/api/auth")) {
            return chain.filter(exchange);
        }
		System.out.println("endpoint is not global");
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
		System.out.println(" JwtGatewayFilter method end ");
		return chain.filter(exchange);
	}
	
	@Override
	public int getOrder() {
		return -1;
	}

}
