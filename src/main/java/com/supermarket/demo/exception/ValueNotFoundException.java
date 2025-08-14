package com.supermarket.demo.exception;

public class ValueNotFoundException extends RuntimeException{
    public ValueNotFoundException(String message) {
        super(message);
    }
}
