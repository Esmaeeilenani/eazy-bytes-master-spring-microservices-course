package com.esmaeeil.eazybank.accounts.controller;

import com.esmaeeil.eazybank.accounts.constants.AccountsConstants;
import com.esmaeeil.eazybank.accounts.dto.CustomerDto;
import com.esmaeeil.eazybank.accounts.properties.AccountsProperties;
import com.esmaeeil.eazybank.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Accounts REST end-point"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountsService accountsService;
    private final AccountsProperties accountsProperties;

    @Operation(
            description = "this end-point is for creating new Customer and by default will create an account for him",
            summary = "Create Customer Account API",

            responses = {
                    @ApiResponse(
                            description = "in case of valid inputs",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "in case of invalid input or duplicate users ",
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )


            }
    )
    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(AccountsConstants.MESSAGE_201);
    }

    @GetMapping
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {

        return ResponseEntity.status(HttpStatus.OK).body(accountsService.fetchAccount(mobileNumber));
    }

    @PutMapping
    public ResponseEntity<String> updateAccountDetails(@RequestBody CustomerDto customerDto) {
        accountsService.updateAccount(customerDto);
        return ResponseEntity.ok().body(AccountsConstants.MESSAGE_200);
    }

    @GetMapping("contact-info")
    public ResponseEntity<AccountsProperties> getAccountsProperties() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsProperties);
    }




}
