package com.esmaeeil.eazybank.gatewayserver.integration.loans;

import reactor.core.publisher.Mono;

public interface LoansClient {

   Mono<LoansSummery> fetchLoanDetails(String mobileNumber);
}
