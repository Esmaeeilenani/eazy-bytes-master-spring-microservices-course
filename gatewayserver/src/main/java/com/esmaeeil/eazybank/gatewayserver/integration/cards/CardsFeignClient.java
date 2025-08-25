package com.esmaeeil.eazybank.gatewayserver.integration.cards;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "cards", path = "api/cards")
 interface CardsFeignClient {

    @GetMapping
    Mono<CardsSummary> fetchCardDetails(@RequestParam String mobileNumber);


}
