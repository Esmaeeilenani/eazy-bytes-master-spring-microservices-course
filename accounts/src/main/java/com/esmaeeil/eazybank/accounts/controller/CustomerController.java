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
            String mobileNumber,
            @RequestHeader(name = "eazybank-correlation-id", required = false) String correlationId
    ) {
        log.debug("eazybank-correlation-id found: {}", correlationId);

        return ResponseEntity.ok(customerService.getCustomerFullDetailsByMobileNumber(mobileNumber));
    }


}
