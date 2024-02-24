package com.taass.seeyousun.resortreservationservice.exceptions;

import java.io.Serial;

public class SunbedNotValidException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public SunbedNotValidException(String message) {
        super(message);
    }
}
