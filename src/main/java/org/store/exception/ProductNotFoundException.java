package org.store.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        throw new RuntimeException(message);
    };

    public ProductNotFoundException(String message, Throwable throwable){
        throw new RuntimeException(message, throwable);
    };
}
