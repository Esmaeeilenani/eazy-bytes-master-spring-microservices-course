package com.esmaeeil.eazybank.gatewayserver.controller;

import com.esmaeeil.eazybank.gatewayserver.dto.CustomerAggregationDto;
import com.esmaeeil.eazybank.gatewayserver.service.CustomerAggregatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/customer-aggregator")
public class CustomerAggregatorController {

    private final CustomerAggregatorService customerAggregatorService;

    public CustomerAggregatorController(CustomerAggregatorService customerAggregatorService) {
        this.customerAggregatorService = customerAggregatorService;
    }

    @GetMapping
    public Mono<ResponseEntity<CustomerAggregationDto>> getAccountSummary(@RequestParam String mobileNumber) {
        return customerAggregatorService
                .getCustomerAggregationByMobileNumber(mobileNumber)
                .map(c -> ResponseEntity.ok().body(c));
    }


}
