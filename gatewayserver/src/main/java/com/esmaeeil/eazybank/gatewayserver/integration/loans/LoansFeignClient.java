package com.esmaeeil.eazybank.gatewayserver.integration.loans;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(name = "loans", path = "api/loans", fallback = LoansFeignClient.Fallback.class)
interface LoansFeignClient {

    @GetMapping
    Mono<LoansSummery> fetchLoanDetails(@RequestParam String mobileNumber);

    @Component
    class Fallback implements LoansFeignClient {

        @Override
        public Mono<LoansSummery> fetchLoanDetails(String mobileNumber) {
            return Mono.just(LoansSummery.notFound());
        }
    }


}
