package com.esmaeeil.eazybank.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Schema(
        name = "Account",
        description = "Schema to hold Customer Account information"
)
public class AccountsDto implements Serializable {


    private Long accountNumber;

    private String accountType;

    private String branchAddress;


}
