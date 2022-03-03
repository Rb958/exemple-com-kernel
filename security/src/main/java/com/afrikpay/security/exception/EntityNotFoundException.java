package com.afrikpay.security.exception;

import dbgateway.exception.DdGatewayException;

public class EntityNotFoundException extends DdGatewayException {
    public EntityNotFoundException(String message) {
        super(4012, message);
    }

    public EntityNotFoundException() {
        super(4012, "Entity not found");
    }
}
