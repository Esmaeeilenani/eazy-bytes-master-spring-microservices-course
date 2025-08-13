package com.esmaeeil.eazybank.accounts.controller;

import com.esmaeeil.eazybank.accounts.constants.AccountsConstants;
import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import com.esmaeeil.eazybank.accounts.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountsService accountsService;

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(AccountsConstants.MESSAGE_201);
    }

}
