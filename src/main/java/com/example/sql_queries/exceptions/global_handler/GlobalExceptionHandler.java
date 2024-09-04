package com.example.sql_queries.exceptions.global_handler;

import com.example.sql_queries.exceptions.individual_exceptions.DuplicateEntryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntryException(DuplicateEntryException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
