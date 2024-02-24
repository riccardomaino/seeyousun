package com.taass.seeyousun.resortreservationservice.exceptions;

import java.io.Serial;

public class ResortNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public ResortNotFoundException(String message) {
        super(message);
    }
}
