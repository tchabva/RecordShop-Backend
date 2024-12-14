package com.northcoders.recordshopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(objectError ->{
            String fieldName = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(
               errors,
                HttpStatus.BAD_REQUEST
        );
    }
}
