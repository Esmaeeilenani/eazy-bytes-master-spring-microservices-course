package com.esmaeeil.eazybank.accounts.integration.loans;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
 class LoansClientService implements LoansClient {

    private final LoansFeignClient loansFeignClient;

    @Override
    public LoansDto fetchLoanDetails(String mobileNumber) {
        return loansFeignClient.fetchLoanDetails(mobileNumber).getBody();
    }
}
