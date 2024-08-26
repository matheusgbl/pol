package com.example.pol;

import com.example.pol.service.LegalCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LegalCaseService.CaseAlreadyExistsException.class)
    public ResponseEntity<String> handleCaseAlreadyExistsException(LegalCaseService.CaseAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}