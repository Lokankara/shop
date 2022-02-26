package org.store.exception;

public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException (String message) {
            super(message);
        }
    public UserNotFoundException(String message, Throwable throwable){
        throw new RuntimeException(message, throwable);
    };
}
