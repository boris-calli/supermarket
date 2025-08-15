package com.supermarket.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ResourceExistsException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public ResourceExistsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
