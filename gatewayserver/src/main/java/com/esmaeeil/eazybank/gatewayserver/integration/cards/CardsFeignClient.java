package com.esmaeeil.eazybank.gatewayserver.integration.cards;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "cards", path = "api/cards", fallback = CardsFeignClient.Fallback.class)
interface CardsFeignClient {

    @GetMapping
    Mono<CardsSummary> fetchCardDetails(@RequestParam String mobileNumber);

    @Component
    class Fallback implements CardsFeignClient {
        @Override
        public Mono<CardsSummary> fetchCardDetails(String mobileNumber) {
            return Mono.just(CardsSummary.notFound());
        }
    }

}
