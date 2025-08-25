package com.esmaeeil.eazybank.gatewayserver.integration.loans;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
 class LoansFeignClientService implements LoansClient {

    private final LoansFeignClient loansFeignClient;

    LoansFeignClientService(LoansFeignClient loansFeignClient) {
        this.loansFeignClient = loansFeignClient;
    }

    @Override
    public Mono<LoansSummery> fetchLoanDetails(String mobileNumber) {
        return loansFeignClient.fetchLoanDetails(mobileNumber);
    }
}
