package com.supermarket.demo.exception;

public class UniqueUsernameException extends RuntimeException {
    public UniqueUsernameException(String message) {
        super(message);
    }
}
