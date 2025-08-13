package com.esmaeeil.eazybank.accounts.service;

import com.esmaeeil.eazybank.accounts.constants.AccountsConstants;
import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import com.esmaeeil.eazybank.accounts.entity.Accounts;
import com.esmaeeil.eazybank.accounts.entity.Customer;
import com.esmaeeil.eazybank.accounts.exception.CustomerAlreadyExistsException;
import com.esmaeeil.eazybank.accounts.mapper.CustomerMapper;
import com.esmaeeil.eazybank.accounts.repository.AccountsRepository;
import com.esmaeeil.eazybank.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;


    public void createAccount(CustomerDto customerDto) {
        if (customerRepository.existsByMobileNumber(customerDto.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists");
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        customerRepository.save(customer);

        accountsRepository.save(createNewAccount(customer));


    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }


}
