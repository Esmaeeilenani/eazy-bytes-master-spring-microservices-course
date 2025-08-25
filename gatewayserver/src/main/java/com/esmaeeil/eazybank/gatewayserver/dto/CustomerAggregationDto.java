package com.esmaeeil.eazybank.gatewayserver.dto;

import com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto.AccountSummary;
import com.esmaeeil.eazybank.gatewayserver.integration.cards.CardsSummary;
import com.esmaeeil.eazybank.gatewayserver.integration.loans.LoansSummery;

public class CustomerAggregationDto {

    private AccountSummary accountSummary;

    private CardsSummary cardsSummary;

    private LoansSummery loansSummery;

    public CustomerAggregationDto(AccountSummary accountSummary, CardsSummary cardsSummary, LoansSummery loansSummery) {
        this.accountSummary = accountSummary;
        this.cardsSummary = cardsSummary;
        this.loansSummery = loansSummery;
    }

    public AccountSummary getAccountSummary() {
        return accountSummary;
    }

    public void setAccountSummary(AccountSummary accountSummary) {
        this.accountSummary = accountSummary;
    }


    public CardsSummary getCardsSummary() {
        return cardsSummary;
    }

    public void setCardsSummary(CardsSummary cardsSummary) {
        this.cardsSummary = cardsSummary;
    }

    public LoansSummery getLoansSummery() {
        return loansSummery;
    }

    public void setLoansSummery(LoansSummery loansSummery) {
        this.loansSummery = loansSummery;
    }
}
