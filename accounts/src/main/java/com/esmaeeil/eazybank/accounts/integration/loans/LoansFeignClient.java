package com.esmaeeil.eazybank.accounts.integration.loans;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* @FeignClient(
* name = should match what exists in eureka
 * url = base url of the service, in case the required service is registered to eureka no need for the URL
 * path =  base path for the service
* )
* */
@FeignClient(name = "loans", path = "api/loans")
 interface LoansFeignClient {

    @GetMapping
     ResponseEntity<LoansDto > fetchLoanDetails(@RequestParam String mobileNumber);


}
