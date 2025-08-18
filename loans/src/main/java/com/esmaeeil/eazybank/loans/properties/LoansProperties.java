package com.esmaeeil.eazybank.loans.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "loans")
public class LoansProperties {

    private String message;

    private ContactDetails contactDetails;

    private List<String> onCallSupport;


    @Data
    public static class ContactDetails {
        private String name;
        private String email;

    }
}


