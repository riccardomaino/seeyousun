package com.taass.seeyousun.apigateway.exceptions;

import java.io.Serial;

public class InvalidAuthenticationTokenException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;

    public InvalidAuthenticationTokenException(String message) {
        super(message);
    }
}
