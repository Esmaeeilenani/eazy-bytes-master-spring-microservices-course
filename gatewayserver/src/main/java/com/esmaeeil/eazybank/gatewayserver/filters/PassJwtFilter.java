package com.esmaeeil.eazybank.gatewayserver.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;


@Order(3)
@Component
public class PassJwtFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(PassJwtFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return exchange.getPrincipal()
                .cast(JwtAuthenticationToken.class)
                .flatMap(jwtAuth -> {
                    logger.info("eazyBank-jwtAuth : {}", jwtAuth);

                    String bearer = "Bearer " + jwtAuth.getToken().getTokenValue();

                    ServerHttpRequest newReq = exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION, bearer).build();

//                    exchange.getRequest().getHeaders().add(HttpHeaders.AUTHORIZATION, bearer);
                    return chain.filter(exchange.mutate().request(newReq).build());
                }).switchIfEmpty(chain.filter(exchange));

    }

}
