package com.esmaeeil.eazybank.gatewayserver.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimitConfig {

    @Bean
    public RedisRateLimiter redisRateLimiter() {
//        ReplenishRate ->  how many tokens generated per sec
//        BurstCapacity -> max number of tokens per bucket
//        RequestedTokens -> how many token consumed per request
        return new RedisRateLimiter(1, 10, 10);
    }

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getQueryParams().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }


}
