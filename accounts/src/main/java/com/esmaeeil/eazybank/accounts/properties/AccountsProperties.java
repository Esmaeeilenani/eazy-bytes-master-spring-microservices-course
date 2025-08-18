package com.esmaeeil.eazybank.accounts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "accounts")
public class AccountsProperties {

    private String message;

    private ContactDetails contactDetails;

    private List<String> onCallSupport;

    public  record ContactDetails(String name, String email) {}
}


