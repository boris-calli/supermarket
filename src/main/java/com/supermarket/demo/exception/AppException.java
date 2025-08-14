package com.supermarket.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.supermarket.demo.dto.ErrorDto;

@ControllerAdvice
public class AppException {
    
    @ExceptionHandler(ValueNotFoundException.class)
    public ResponseEntity<ErrorDto> handleValueNotFoundException(ValueNotFoundException ex) {
        String message = ex.getMessage();
        String code = HttpStatus.NOT_FOUND.toString();
        ErrorDto error = ErrorDto.builder().code(code).message(message).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueUsernameException.class)
    public ResponseEntity<ErrorDto> handleUniqueUsernameException(UniqueUsernameException ex) {
        String message = ex.getMessage();
        String code = HttpStatus.PRECONDITION_FAILED.toString();
        ErrorDto error = ErrorDto.builder().code(code).message(message).build();
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

}
