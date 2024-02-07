package com.taass.seeyousun.resortservice.exceptions;

import java.io.Serial;

public class ServiceNotReachableException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;
    public ServiceNotReachableException(String message) {
        super(message);
    }
}
