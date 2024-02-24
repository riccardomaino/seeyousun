package com.taass.seeyousun.resortreservationservice.exceptions;

import java.io.Serial;

public class UmbrellaOutOfBoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public UmbrellaOutOfBoundException(String message) {
        super(message);
    }

}
