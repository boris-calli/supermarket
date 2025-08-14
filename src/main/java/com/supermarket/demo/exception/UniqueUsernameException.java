package com.supermarket.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class UniqueUsernameException extends RuntimeException {
    @Getter
    private final HttpStatus status;
    public UniqueUsernameException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
