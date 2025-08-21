package com.esmaeeil.eazybank.accounts.integration.loans;

import org.springframework.web.bind.annotation.RequestParam;

public interface LoansClient {

    LoansDto fetchLoanDetails(@RequestParam String mobileNumber);
}
