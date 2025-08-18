package com.esmaeeil.eazybank.accounts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "accounts")
public class AccountsProperties {

    private String message;

    private ContactDetails contactDetails;

    private List<String> onCallSupport;

    @Data
    public static class ContactDetails {
        private String name;
       private String email;

    }
}


