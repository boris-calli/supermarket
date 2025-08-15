package com.supermarket.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.supermarket.demo.dto.ErrorDto;

@ControllerAdvice
public class AppException {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ErrorDto error = ErrorDto.builder()
        .code(ex.getStatus())
        .message(message)
        .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ErrorDto> handleResourceExistsException(ResourceExistsException ex) {
        String message = ex.getMessage();
        ErrorDto error = ErrorDto.builder()
        .code(ex.getStatus())
        .message(message)
        .time(LocalDateTime.now())
        .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidException(MethodArgumentNotValidException ex) {
        ErrorDto error = ErrorDto.builder()
        .code(HttpStatus.BAD_REQUEST)
        .message("Fallido")
        .time(LocalDateTime.now())
        .build();
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
