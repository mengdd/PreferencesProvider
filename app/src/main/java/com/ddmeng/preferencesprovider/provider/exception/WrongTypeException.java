package com.ddmeng.preferencesprovider.provider.exception;

public class WrongTypeException extends RuntimeException {
    public WrongTypeException() {
    }

    public WrongTypeException(String message) {
        super(message);
    }

    public WrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongTypeException(Throwable cause) {
        super(cause);
    }
}
