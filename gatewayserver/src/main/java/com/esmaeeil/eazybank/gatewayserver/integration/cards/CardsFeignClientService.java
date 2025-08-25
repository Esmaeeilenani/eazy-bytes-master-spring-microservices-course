package com.esmaeeil.eazybank.gatewayserver.integration.cards;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CardsFeignClientService implements CardsClient {

    private final CardsFeignClient cardsFeignClient;

    public CardsFeignClientService(CardsFeignClient cardsFeignClient) {
        this.cardsFeignClient = cardsFeignClient;
    }

    @Override
    public Mono<CardsSummary> fetchCardDetails(String mobileNumber) {
        return cardsFeignClient.fetchCardDetails(mobileNumber);
    }
}
