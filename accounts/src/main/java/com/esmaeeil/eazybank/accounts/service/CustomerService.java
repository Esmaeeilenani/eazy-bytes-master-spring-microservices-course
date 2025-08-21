package com.esmaeeil.eazybank.accounts.service;

import com.esmaeeil.eazybank.accounts.dto.AccountsDto;
import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import com.esmaeeil.eazybank.accounts.dto.CustomerFullDetailsDto;
import com.esmaeeil.eazybank.accounts.entity.Accounts;
import com.esmaeeil.eazybank.accounts.entity.Customer;
import com.esmaeeil.eazybank.accounts.exception.ResourceNotFoundException;
import com.esmaeeil.eazybank.accounts.mapper.AccountsMapper;
import com.esmaeeil.eazybank.accounts.mapper.CustomerMapper;
import com.esmaeeil.eazybank.accounts.repository.CustomerRepository;
import com.esmaeeil.eazybank.accounts.service.client.CardsFeignClient;
import com.esmaeeil.eazybank.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomerService {

    private final AccountsService accountsService;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;
    private final CustomerRepository customerRepository;


    public CustomerFullDetailsDto getCustomerFullDetailsByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with mobile number " + mobileNumber + " not found"));

        AccountsDto accountsDto = accountsService.getAccountsByCustomerId(customer.getCustomerId());

        CustomerFullDetailsDto customerDto = CustomerMapper.mapToCustomerFullDetailsDto(customer);

        customerDto.setAccountsDto(accountsDto);
        customerDto.setCardsDto(cardsFeignClient.fetchCardDetails(mobileNumber).getBody());
        customerDto.setLoansDto(loansFeignClient.fetchLoanDetails(mobileNumber).getBody());


        return customerDto;

    }
}
