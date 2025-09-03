package com.esmaeeil.eazybank.gatewayserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {


        return http
                .authorizeExchange(spec -> spec
                        .anyExchange().hasRole("ACCOUNTS"))
                .oauth2ResourceServer(oAuth2Config ->
                        oAuth2Config.jwt(jwtSpec ->
                                jwtSpec
                                        .jwtAuthenticationConverter(getGrantedAuthoritiesConverter())
                                )
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }


    private Converter<Jwt, Mono<AbstractAuthenticationToken>> getGrantedAuthoritiesConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtRoleConverter());

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
