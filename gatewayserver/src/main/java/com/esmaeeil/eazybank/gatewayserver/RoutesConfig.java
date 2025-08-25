package com.esmaeeil.eazybank.gatewayserver;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class RoutesConfig {

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
        Map<String, String> routesConfig = new LinkedHashMap<>();
        routesConfig.put("ACCOUNTS", "/eazybank/accounts");
        routesConfig.put("ACCOUNTS:customers", "/eazybank/customers");
        routesConfig.put("CARDS", "/eazybank/cards");
        routesConfig.put("LOANS", "/eazybank/loans");


        RouteLocatorBuilder.Builder routes = builder.routes();

        for (Map.Entry<String, String> entry : routesConfig.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            String routURI;
            String secondEntry;
            int indexOfColons = key.indexOf(":");
            if (indexOfColons > 0) {
                routURI = key.substring(0, indexOfColons);
                secondEntry = key.substring(indexOfColons + 1);
            } else {
                routURI = key;
                secondEntry = null;
            }
            routes = routes
                    .route(pred ->
                            pred
                                    .path(value + "/**")
                                    .filters(f ->
                                            f
                                                    .rewritePath(value + "(?<segment>/?.*)", "/api/" + Objects.requireNonNullElseGet(secondEntry, routURI::toLowerCase).toLowerCase() + "${segment}")
                                                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                                    .circuitBreaker(config -> config.setName(key + "-circuit-breaker")
                                                            .setFallbackUri("forward:/contact-support"))

                                    )
                                    .uri("lb://" + routURI.toUpperCase())
                    );


        }


        return routes.build();


//
//        return builder.routesConfig()
//                .route(predicateSpec ->
//                        predicateSpec
//                                //this path is what client enters from their side
//                                .path("/eazybank/accounts/**")
//                                .filters(f -> f
//                                        // this rewrite path is for whenever client enters the path change it with the replaced one and keep the segment included
//                                        // /eazybank/accounts path entered by client in the gateway, (?<segment>/?.*) might be an end-point or query param
//                                        // /api/accounts is the service entry-point, ${segment} to carry the needed end-point or query param
//                                        .rewritePath("/eazybank/accounts(?<segment>/?.*)", "/api/accounts${segment}"))
//                                // the service URL to call with the rewritten path
//                                .uri("lb://ACCOUNTS")
//                ).route(predicateSpec ->
//                        predicateSpec
//                                .path("/eazybank/customers/**")
//                                .filters(f -> f
//                                        .rewritePath("/eazybank/customers(?<segment>/?.*)", "/api/customers${segment}"))
//                                .uri("lb://ACCOUNTS")
//                )
//                .route(predicateSpec ->
//                        predicateSpec
//                                .path("/eazybank/cards/**")
//                                .filters(f -> f
//                                        .rewritePath("/eazybank/cards(?<segment>/?.*)", "/api/cards${segment}"))
//                                .uri("lb://CARDS")
//                ).route(predicateSpec ->
//                        predicateSpec
//                                .path("/eazybank/loans/**")
//                                .filters(f -> f
//                                        .rewritePath("/eazybank/loans(?<segment>/?.*)", "/api/loans${segment}"))
//                                .uri("lb://LOANS")
//                )
//
//
//                .build();

    }

}
