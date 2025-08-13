package com.esmaeeil.eazybank.accounts.controller;

import com.esmaeeil.eazybank.accounts.constants.AccountsConstants;
import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts")
public class AccountController {


    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CustomerDto customerDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(AccountsConstants.MESSAGE_201);
    }

}
