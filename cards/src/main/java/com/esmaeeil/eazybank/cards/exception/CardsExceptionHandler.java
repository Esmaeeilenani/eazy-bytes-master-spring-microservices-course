package com.esmaeeil.eazybank.cards.exception;

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
public class CardsExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex, ServletWebRequest request) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ProblemDetail problemDetail = problemDetailFactory("Exception", ex, internalServerError, request);

        return ResponseEntity.status(internalServerError).body(problemDetail);
    }

    @ExceptionHandler(CardsAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleException(CardsAlreadyExistsException ex, ServletWebRequest request) {


        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ProblemDetail problemDetail = problemDetailFactory("CustomerAlreadyExists", ex, badRequest, request);
        return ResponseEntity.status(badRequest).body(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleException(ResourceNotFoundException ex, ServletWebRequest request) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ProblemDetail problemDetail = problemDetailFactory("ResourceNotFound", ex, notFound, request);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }


    private ProblemDetail problemDetailFactory(String handlerName, Exception ex, HttpStatus httpStatus, ServletWebRequest request) {
        log.error("{} at {}:{}",
                handlerName, request.getHttpMethod(), request.getRequest().getRequestURI(), ex);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(httpStatus, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now().toString());

        return detail;
    }


}
