package com.esmaeeil.eazybank.accounts.service;

import com.esmaeeil.eazybank.accounts.constants.AccountsConstants;
import com.esmaeeil.eazybank.accounts.dto.AccountsDto;
import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import com.esmaeeil.eazybank.accounts.entity.Accounts;
import com.esmaeeil.eazybank.accounts.entity.Customer;
import com.esmaeeil.eazybank.accounts.exception.CustomerAlreadyExistsException;
import com.esmaeeil.eazybank.accounts.exception.ResourceNotFoundException;
import com.esmaeeil.eazybank.accounts.mapper.AccountsMapper;
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


    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with mobile number " + mobileNumber + " not found"));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account with customer Id: " + customer.getCustomerId() + " not found"));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));


        return customerDto;
    }

    public void updateAccount(CustomerDto customerDto) {
        if (customerDto == null) {
            return;
        }
        AccountsDto accountsDto = customerDto.getAccountsDto();
        Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Customer with mobile number " + customerDto.getMobileNumber() + " not found"));


        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account with Id: " + accountsDto.getAccountNumber() + " not found"));

            if (accountsDto.getAccountType() != null) {
                accounts.setAccountType(accountsDto.getAccountType());
            }
            if (accountsDto.getBranchAddress() != null) {
                accounts.setBranchAddress(accountsDto.getBranchAddress());
            }
            accounts.setUpdatedAt(LocalDateTime.now());
            accounts.setUpdatedBy("Anonymous");

            accountsRepository.save(accounts);

        }


        if (customerDto.getName() != null) {
            customer.setName(customerDto.getName());
        }

        if (customerDto.getEmail() != null) {
            customer.setEmail(customerDto.getEmail());
        }
        if (customerDto.getMobileNumber() != null) {
            customer.setMobileNumber(customerDto.getMobileNumber());
        }

        customerRepository.save(customer);


    }
}
