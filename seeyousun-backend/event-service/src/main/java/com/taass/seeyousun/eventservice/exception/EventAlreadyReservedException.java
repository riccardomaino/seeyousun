package com.taass.seeyousun.eventservice.exception;

import java.io.Serial;

public class EventAlreadyReservedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public EventAlreadyReservedException(String message) {
        super(message);
    }
}
