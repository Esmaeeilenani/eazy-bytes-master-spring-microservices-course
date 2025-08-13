package com.esmaeeil.eazybank.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class AccountExceptionHandler {


    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleException(CustomerAlreadyExistsException ex) {


        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
    }


}
