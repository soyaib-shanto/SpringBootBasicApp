package com.example.SpringBootIntern.ExceptionHandler;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
