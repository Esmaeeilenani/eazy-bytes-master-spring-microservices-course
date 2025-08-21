package com.esmaeeil.eazybank.accounts.controller;


import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import com.esmaeeil.eazybank.accounts.dto.CustomerFullDetailsDto;
import com.esmaeeil.eazybank.accounts.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/customers")

public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerFullDetailsDto> getCustomer(
            @Valid @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        return ResponseEntity.ok(customerService.getCustomerFullDetailsByMobileNumber(mobileNumber));
    }


}
