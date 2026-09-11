package com.reservas.commons.exception;

public class InvalidOperationException extends BadRequestException {

    public static final String CODE = "INVALID_OPERATION";

    public InvalidOperationException(String message) {
        super(CODE, message);
    }
}
