package com.esmaeeil.eazybank.gatewayserver.integration.accounts;

import com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto.AccountSummary;
import reactor.core.publisher.Mono;

public interface AccountsClient {

    Mono<AccountSummary> getAccountSummaryByMobileNumber(String mobileNumber);

}
