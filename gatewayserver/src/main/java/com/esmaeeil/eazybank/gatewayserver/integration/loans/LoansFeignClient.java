package com.esmaeeil.eazybank.gatewayserver.integration.loans;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(name = "loans", path = "api/loans")
interface LoansFeignClient {

    @GetMapping
    Mono<LoansSummery> fetchLoanDetails(@RequestParam String mobileNumber);


}
