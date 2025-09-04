package com.esmaeeil.eazybank.accounts.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class AccountsFunctions {


    @Bean
    public Consumer<Long> updateCommunication() {
        return accountNumber -> {
            log.info("Received account message from RabbitMQ with account number {}", accountNumber);

        };
    }
}
