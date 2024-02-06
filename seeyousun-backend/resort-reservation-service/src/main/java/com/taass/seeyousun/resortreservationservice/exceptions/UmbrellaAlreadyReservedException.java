package com.taass.seeyousun.resortreservationservice.exceptions;

import java.io.Serial;

public class UmbrellaAlreadyReservedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public UmbrellaAlreadyReservedException(String message) {
        super(message);
    }
}
