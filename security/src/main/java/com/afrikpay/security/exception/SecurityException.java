package com.afrikpay.security.exception;

public class SecurityException extends Exception{
    protected final int code;

    public SecurityException(int code, String message) {
        super(message);
        this.code = code;
    }
}
