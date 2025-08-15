package com.supermarket.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException{
    @Getter
    private final HttpStatus status;

    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
