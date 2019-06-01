package com.dyvak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ApplicationException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}

