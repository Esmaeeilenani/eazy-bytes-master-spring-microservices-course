package com.esmaeeil.eazybank.gatewayserver.integration.accounts;

import com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto.AccountSummary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(name = "accounts", path = "api/accounts", fallback = AccountsFeignClient.Fallback.class)
interface AccountsFeignClient {

    @GetMapping
    Mono<AccountSummary> getAccountSummaryByMobileNumber(@RequestParam String mobileNumber);

    @Component
    class Fallback implements AccountsFeignClient {

        @Override
        public Mono<AccountSummary> getAccountSummaryByMobileNumber(String mobileNumber) {
            return Mono.just(AccountSummary.notFound());
        }
    }
}
