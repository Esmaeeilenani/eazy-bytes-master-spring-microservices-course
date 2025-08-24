package com.esmaeeil.eazybank.gatewayserver;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec
                                //this path is what client enters from their side
                                .path("/eazybank/accounts/**")
                                .filters(f -> f
                                        // this rewrite path is for whenever client enters the path change it with the replaced one and keep the segment included
                                        // /eazybank/accounts path entered by client in the gateway, (?<segment>/?.*) might be an end-point or query param
                                        // /api/accounts is the service entry-point, ${segment} to carry the needed end-point or query param
                                        .rewritePath("/eazybank/accounts(?<segment>/?.*)", "/api/accounts${segment}"))
                                // the service URL to call with the rewritten path
                                .uri("lb://ACCOUNTS")
                ).route(predicateSpec ->
                        predicateSpec
                                .path("/eazybank/customers/**")
                                .filters(f -> f
                                        .rewritePath("/eazybank/customers(?<segment>/?.*)", "/api/customers${segment}"))
                                .uri("lb://ACCOUNTS")
                )
                .route(predicateSpec ->
                        predicateSpec
                                .path("/eazybank/cards/**")
                                .filters(f -> f
                                        .rewritePath("/eazybank/cards(?<segment>/?.*)", "/api/cards${segment}"))
                                .uri("lb://CARDS")
                ).route(predicateSpec ->
                        predicateSpec
                                .path("/eazybank/loans/**")
                                .filters(f -> f
                                        .rewritePath("/eazybank/loans(?<segment>/?.*)", "/api/loans${segment}"))
                                .uri("lb://LOANS")
                )


                .build();

    }

}
