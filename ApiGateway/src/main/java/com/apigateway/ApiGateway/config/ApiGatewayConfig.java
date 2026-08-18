package com.apigateway.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class ApiGatewayConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // Your JwtGatewayFilter already enforces auth manually,
                // so permit everything here and let that filter be the gate.
                .authorizeExchange(auth -> auth.anyExchange().permitAll())
                .build();
    }
}