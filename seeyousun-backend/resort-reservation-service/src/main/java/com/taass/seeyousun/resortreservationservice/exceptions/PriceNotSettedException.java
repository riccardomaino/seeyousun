package com.taass.seeyousun.resortreservationservice.exceptions;

import java.io.Serial;

public class PriceNotSettedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public PriceNotSettedException(String message) {
        super(message);
    }
}
