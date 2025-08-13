package com.esmaeeil.eazybank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class CustomerDto {

    @NotBlank(message = "name should not be null or empty")
    private String name;

    @NotBlank(message = "email should not be null or empty")
    private String email;

    @NotBlank(message = "mobileNumber should not be null or empty")
    @Length(min = 10, max = 10, message = "mobileNumber should be 10 digits only!")
    private String mobileNumber;


    private AccountsDto accountsDto;
}
