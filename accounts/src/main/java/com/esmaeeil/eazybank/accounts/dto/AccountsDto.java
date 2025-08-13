package com.esmaeeil.eazybank.accounts.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AccountsDto implements Serializable {


    private Long accountNumber;

    private String accountType;

    private String branchAddress;


}
