package com.esmaeeil.eazybank.message.functions;

import com.esmaeeil.eazybank.message.dto.AccountsMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class MessageFunctions {


    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        return accountsMsgDto -> {
            log.info("sending email with following details: {}", accountsMsgDto);
            return accountsMsgDto;
        };
    }


    @Bean
    public Function<AccountsMsgDto, Long> sms() {
        return accountsMsgDto -> {
            log.info("sending sms with following details: {}", accountsMsgDto);
            return accountsMsgDto.accountNumber();
        };
    }





}
