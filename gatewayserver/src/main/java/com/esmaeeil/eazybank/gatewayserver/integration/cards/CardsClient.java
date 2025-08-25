package com.esmaeeil.eazybank.gatewayserver.integration.cards;

import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

public interface CardsClient {

    Mono<CardsSummary> fetchCardDetails(@RequestParam String mobileNumber);

}
