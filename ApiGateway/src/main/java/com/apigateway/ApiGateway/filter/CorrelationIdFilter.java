package com.apigateway.ApiGateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.ApiGateway.utils.CorrelationIdUtils;

import reactor.core.publisher.Mono;

@Component
public class CorrelationIdFilter implements GlobalFilter, Ordered {

	private final CorrelationIdUtils correlationIdUtils;

	public CorrelationIdFilter(CorrelationIdUtils correlationIdUtils) {
		this.correlationIdUtils = correlationIdUtils;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String correlationId = exchange.getRequest().getHeaders().getFirst(CorrelationIdUtils.CORRELATION_ID);
		if (correlationId == null || correlationId.isBlank()) {
			correlationId = correlationIdUtils.generateId();
			System.out.println("creating new Id" );
		}
		System.out.println(correlationId);
		ServerWebExchange modifiedExchange = exchange.mutate()
				.request(exchange.getRequest().mutate().header(CorrelationIdUtils.CORRELATION_ID, correlationId).build())
				.build();
		
		modifiedExchange.getResponse().getHeaders().set(CorrelationIdUtils.CORRELATION_ID, correlationId);

		return chain.filter(modifiedExchange);

	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

}
