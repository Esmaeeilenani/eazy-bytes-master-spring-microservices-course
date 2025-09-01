package com.esmaeeil.eazybank.accounts.controller;


import com.esmaeeil.eazybank.accounts.dto.CustomerFullDetailsDto;
import com.esmaeeil.eazybank.accounts.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        log.debug("fetching Customer Full Details");
        CustomerFullDetailsDto customerFullDetailsByMobileNumber = customerService.getCustomerFullDetailsByMobileNumber(mobileNumber);
        log.debug("fetching Customer Full Details  finished");
        return ResponseEntity.ok(customerFullDetailsByMobileNumber);
    }


}
