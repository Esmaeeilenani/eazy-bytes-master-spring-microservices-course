package com.esmaeeil.eazybank.accounts.service;

import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import com.esmaeeil.eazybank.accounts.repository.AccountsRepository;
import com.esmaeeil.eazybank.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;


    public void createAccount(CustomerDto customerDto) {


    }


}
