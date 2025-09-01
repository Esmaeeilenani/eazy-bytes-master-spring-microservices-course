package com.esmaeeil.eazybank.gatewayserver.service;

import com.esmaeeil.eazybank.gatewayserver.dto.CustomerAggregationDto;
import com.esmaeeil.eazybank.gatewayserver.integration.accounts.AccountsClient;
import com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto.AccountSummary;
import com.esmaeeil.eazybank.gatewayserver.integration.cards.CardsClient;
import com.esmaeeil.eazybank.gatewayserver.integration.cards.CardsSummary;
import com.esmaeeil.eazybank.gatewayserver.integration.loans.LoansClient;
import com.esmaeeil.eazybank.gatewayserver.integration.loans.LoansSummery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
@Service
public class CustomerAggregatorService {

    private final AccountsClient accountsClient;

    private final CardsClient cardsClient;
    private final LoansClient loansClient;

    private static final Logger log = LoggerFactory.getLogger(CustomerAggregatorService.class);

    public CustomerAggregatorService(AccountsClient accountsClient, CardsClient cardsClient, LoansClient loansClient) {
        this.accountsClient = accountsClient;
        this.cardsClient = cardsClient;
        this.loansClient = loansClient;
    }


    public Mono<CustomerAggregationDto> getCustomerAggregationByMobileNumber(String mobileNumber) {
        log.debug("fetching Customer Full Details using API Aggregation");
        Mono<AccountSummary> accountSummary = accountsClient.getAccountSummaryByMobileNumber(mobileNumber)
                .timeout(Duration.ofSeconds(5))
                .subscribeOn(Schedulers.boundedElastic());

        Mono<CardsSummary> cardsSummary = cardsClient.fetchCardDetails(mobileNumber)
                .timeout(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic());


        Mono<LoansSummery> loansSummery = loansClient.fetchLoanDetails(mobileNumber)
                .timeout(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic());

        log.debug("fetching Customer Full Details using API Aggregation  finished");

        return Mono.zip(accountSummary, cardsSummary, loansSummery)
                .map(t -> new CustomerAggregationDto(t.getT1(), t.getT2(), t.getT3()));

    }


}
