package com.esmaeeil.eazybank.gatewayserver.service;

import com.esmaeeil.eazybank.gatewayserver.dto.CustomerAggregationDto;
import com.esmaeeil.eazybank.gatewayserver.integration.accounts.AccountsClient;
import com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto.AccountSummary;
import com.esmaeeil.eazybank.gatewayserver.integration.cards.CardsClient;
import com.esmaeeil.eazybank.gatewayserver.integration.cards.CardsSummary;
import com.esmaeeil.eazybank.gatewayserver.integration.loans.LoansClient;
import com.esmaeeil.eazybank.gatewayserver.integration.loans.LoansSummery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
public class CustomerAggregatorService {

    private final AccountsClient accountsClient;

    private final CardsClient cardsClient;
    private final LoansClient loansClient;


    public CustomerAggregatorService(AccountsClient accountsClient, CardsClient cardsClient, LoansClient loansClient) {
        this.accountsClient = accountsClient;
        this.cardsClient = cardsClient;
        this.loansClient = loansClient;
    }


    public Mono<CustomerAggregationDto> getCustomerAggregationByMobileNumber(String mobileNumber) {

        Mono<AccountSummary> accountSummary = accountsClient.getAccountSummaryByMobileNumber(mobileNumber)
                .timeout(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorReturn(AccountSummary.notFound());

        Mono<CardsSummary> cardsSummary = cardsClient.fetchCardDetails(mobileNumber)
                .timeout(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorReturn(CardsSummary.notFound());


        Mono<LoansSummery> loansSummery = loansClient.fetchLoanDetails(mobileNumber)
                .timeout(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorReturn(LoansSummery.notFound());


        return Mono.zip(accountSummary, cardsSummary, loansSummery)
                .map(t -> new CustomerAggregationDto(t.getT1(), t.getT2(), t.getT3()));

    }


}
