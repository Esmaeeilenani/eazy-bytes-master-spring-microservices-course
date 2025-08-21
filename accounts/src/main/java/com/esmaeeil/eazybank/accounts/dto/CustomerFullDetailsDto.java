package com.esmaeeil.eazybank.accounts.dto;

import com.esmaeeil.eazybank.accounts.integration.cards.CardsDto;
import com.esmaeeil.eazybank.accounts.integration.loans.LoansDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerFullDetailsDto extends CustomerDto {

    private CardsDto cardsDto;
    private LoansDto loansDto;

}
