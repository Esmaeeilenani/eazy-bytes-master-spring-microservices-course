package com.esmaeeil.eazybank.accounts.service.client;

import com.esmaeeil.eazybank.accounts.dto.CardsDto;
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
@FeignClient(name = "cards", path = "api/cards")
public interface CardsFeignClient {

    @GetMapping
     ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);


}
