package com.afrikpay.security.exception;

public class BadDBConfigurationException extends Exception{
    private final int code;

    public BadDBConfigurationException(String message) {
        super(message);
        this.code = 4010;
    }

    public BadDBConfigurationException() {
        super("Bad Database connection configuration");
        this.code = 4010;
    }
}
