package com.esmaeeil.eazybank.accounts.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
}
