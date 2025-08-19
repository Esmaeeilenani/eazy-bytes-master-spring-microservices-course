package com.esmaeeil.eazybank.loans.controller;

import com.esmaeeil.eazybank.loans.constants.LoansConstants;
import com.esmaeeil.eazybank.loans.dto.LoansDto;
import com.esmaeeil.eazybank.loans.properties.LoansProperties;
import com.esmaeeil.eazybank.loans.service.LoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/loans")
public class LoansController {
    private final LoansService loansService;

    private final LoansProperties loansProperties;


    @PostMapping
    public ResponseEntity<String> createLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(LoansConstants.MESSAGE_201);
    }


    @GetMapping
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        LoansDto loansDto = loansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping
    public ResponseEntity<String> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {
        loansService.updateLoan(loansDto);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(LoansConstants.MESSAGE_200);
    }

    @GetMapping("contact-info")
    public ResponseEntity<LoansProperties> getLoansProperties() {
        return ResponseEntity.status(HttpStatus.OK).body(loansProperties);
    }

}
