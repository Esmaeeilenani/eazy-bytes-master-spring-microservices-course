package com.esmaeeil.eazybank.accounts.integration.cards;

import org.springframework.web.bind.annotation.RequestParam;

public interface CardsClient {

    CardsDto fetchCardDetails(@RequestParam String mobileNumber);

}
