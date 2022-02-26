package org.store.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable throwable){
        throw new RuntimeException(message, throwable);
    };
}
