package com.esmaeeil.eazybank.accounts.integration.cards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardsClientService implements CardsClient {

    private final CardsFeignClient cardsFeignClient;

    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        return cardsFeignClient.fetchCardDetails(mobileNumber).getBody();
    }
}
