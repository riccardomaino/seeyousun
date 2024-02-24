package com.taass.seeyousun.eventservice.exception;

import java.io.Serial;

public class EventNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public EventNotFoundException(String message) {
        super(message);
    }
}
