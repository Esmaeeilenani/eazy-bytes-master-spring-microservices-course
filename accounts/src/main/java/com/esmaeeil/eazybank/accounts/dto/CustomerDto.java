package com.esmaeeil.eazybank.accounts.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and its Accounts information"
)
public class CustomerDto {

    @NotBlank(message = "name should not be null or empty")
    @Schema(example = "esmaeeil")
    private String name;

    @NotBlank(message = "email should not be null or empty")
    @Schema(example = "esmaeeil@gmail.com")
    private String email;

    @NotBlank(message = "mobileNumber should not be null or empty")
    @Length(min = 10, max = 10, message = "mobileNumber should be 10 digits only!")
    @Schema(example = "0564896235")
    private String mobileNumber;


    private AccountsDto accountsDto;


    @JsonGetter("accountDetails")
    public AccountsDto getAccountsDto() {
        return accountsDto;
    }
}
