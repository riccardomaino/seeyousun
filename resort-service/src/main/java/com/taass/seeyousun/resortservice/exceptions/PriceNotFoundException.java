package com.taass.seeyousun.resortservice.exceptions;

import java.io.Serial;

public class PriceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;
    public PriceNotFoundException(String message) {
        super(message);
    }
}
