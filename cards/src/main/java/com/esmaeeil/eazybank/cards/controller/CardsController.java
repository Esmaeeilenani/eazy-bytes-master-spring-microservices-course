package com.esmaeeil.eazybank.cards.controller;

import com.esmaeeil.eazybank.cards.constants.CardsConstants;
import com.esmaeeil.eazybank.cards.dto.CardsDto;
import com.esmaeeil.eazybank.cards.service.CardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cards")
@Validated
public class CardsController {

    private final CardsService cardsService;

    @PostMapping
    public ResponseEntity<String> createCard(@Valid @RequestParam
                                             @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                             String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CardsConstants.MESSAGE_201);
    }


    @GetMapping
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }


    @PutMapping
    public ResponseEntity<String> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
        cardsService.updateCard(cardsDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CardsConstants.MESSAGE_200);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteCardDetails(@RequestParam
                                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                    String mobileNumber) {
        boolean isDeleted = cardsService.deleteCard(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CardsConstants.MESSAGE_200);
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(CardsConstants.MESSAGE_417_DELETE);
        }
    }


}
