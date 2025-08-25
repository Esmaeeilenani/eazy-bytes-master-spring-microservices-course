package com.esmaeeil.eazybank.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID_KEY = "eazybank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {

        if (requestHeaders.get(CORRELATION_ID_KEY) != null) {
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID_KEY);
            assert requestHeaderList != null;
            return requestHeaderList.stream().findFirst().orElse(null);
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().header(name, value).build();
        return exchange.mutate().request(serverHttpRequest).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID_KEY, correlationId);
    }


}
