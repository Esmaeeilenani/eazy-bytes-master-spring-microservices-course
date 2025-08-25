package com.esmaeeil.eazybank.gatewayserver.integration.accounts;

import com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto.AccountSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(name = "accounts", path = "api/accounts")
public interface AccountsFeignClient {

    @GetMapping
    Mono<AccountSummary> getAccountSummaryByMobileNumber(@RequestParam String mobileNumber);

}
