package com.msproduit.exceptions;

public class DuplicateProductException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateProductException(String message) {
        super(message);
    }

    public DuplicateProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
