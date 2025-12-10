package com.example.caritas.exception;

public class AlreadyExistsByNomeException extends RuntimeException {
    public AlreadyExistsByNomeException(String message) {
        super(message);
    }
}
