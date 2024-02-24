package com.taass.seeyousun.eventservice.exception;

import java.io.Serial;

public class EventFullReservedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public EventFullReservedException(String message) {
        super(message);
    }
}
