package com.esmaeeil.eazybank.gatewayserver.integration.accounts;

import com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto.AccountSummary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
 class AccountsFeignClientService implements AccountsClient {

    private final AccountsFeignClient accountsFeignClient;

    public AccountsFeignClientService(AccountsFeignClient accountsFeignClient) {
        this.accountsFeignClient = accountsFeignClient;
    }


    @Override
    public Mono<AccountSummary> getAccountSummaryByMobileNumber(String mobileNumber) {
        return accountsFeignClient.getAccountSummaryByMobileNumber(mobileNumber);
    }
}
