package com.taass.seeyousun.eventservice.exception;

import java.io.Serial;

public class EventNotReservedException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;

    public EventNotReservedException(String message) {
        super(message);
    }
}
