package com.taass.seeyousun.resortreservationservice.exceptions;

import java.io.Serial;

public class DailyReservationNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public DailyReservationNotFoundException(String message) {
        super(message);
    }
}
