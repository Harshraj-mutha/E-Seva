package com.apigateway.ApiGateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.ApiGateway.utils.CorrelationIdUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CorrelationIdFilter implements GlobalFilter, Ordered {

	private final CorrelationIdUtils correlationIdUtils;

	public CorrelationIdFilter(CorrelationIdUtils correlationIdUtils) {
		this.correlationIdUtils = correlationIdUtils;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		long startTime = System.currentTimeMillis();
		String correlationId = exchange.getRequest().getHeaders().getFirst(CorrelationIdUtils.CORRELATION_ID);
		if (correlationId == null || correlationId.isBlank()) {
			correlationId = correlationIdUtils.generateId();
			log.info("correlation-id = "+correlationId);
		}
		ServerWebExchange modifiedExchange = exchange.mutate()
				.request(exchange.getRequest().mutate().header(CorrelationIdUtils.CORRELATION_ID, correlationId).build())
				.build();
		
		modifiedExchange.getResponse().getHeaders().set(CorrelationIdUtils.CORRELATION_ID, correlationId);

		return chain.filter(modifiedExchange).doOnSuccess( fun -> {
			long endTime = System.currentTimeMillis()-startTime;
			log.info("time taken by successful request {} ms",endTime);
		}).doOnError( error -> {
			long endTime = System.currentTimeMillis()-startTime;
			log.info("time taken by exception resposne {} ms, error={}",endTime, error);
		});

	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

}
