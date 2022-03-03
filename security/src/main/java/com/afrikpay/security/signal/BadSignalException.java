package com.afrikpay.security.signal;

public final class BadSignalException extends Exception{

    public BadSignalException(){
        super("Bad signal: Please ensure that the payload is valid");
    }
    public BadSignalException(String message) {
        super(message);
    }
}
