package com.taass.seeyousun.apigateway.exceptions;

import java.io.Serial;

public class AuthenticationRequiredException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public AuthenticationRequiredException(String message) {
        super(message);
    }
}
