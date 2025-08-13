package com.esmaeeil.eazybank.accounts.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j
public class AccountExceptionHandler {


    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleException(CustomerAlreadyExistsException ex, ServletWebRequest request) {

        log.error("CustomerAlreadyExists at {}:{}",
                request.getHttpMethod(), request.getRequest().getRequestURI(), ex);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleException(ResourceNotFoundException ex, ServletWebRequest request) {

        log.error("ResourceNotFound at {}:{}",
                request.getHttpMethod(), request.getRequest().getRequestURI(), ex);


        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
    }


}
