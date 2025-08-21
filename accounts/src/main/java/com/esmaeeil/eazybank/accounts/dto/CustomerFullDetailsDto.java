package com.esmaeeil.eazybank.accounts.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerFullDetailsDto extends CustomerDto {

    private CardsDto cardsDto;
    private LoansDto loansDto;

}
