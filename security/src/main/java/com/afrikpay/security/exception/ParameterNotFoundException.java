package com.afrikpay.security.exception;

public class ParameterNotFoundException extends SecurityException {
    public ParameterNotFoundException() {
        super(4021, "Parameter not found");
    }
}
