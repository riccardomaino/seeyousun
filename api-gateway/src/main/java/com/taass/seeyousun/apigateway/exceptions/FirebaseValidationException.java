package com.taass.seeyousun.apigateway.exceptions;

import java.io.Serial;

public class FirebaseValidationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;
    public FirebaseValidationException(String message) {
        super(message);
    }

}
