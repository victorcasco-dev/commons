package com.reservas.commons.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends ApiException {

    public static final String CODE = "INTERNAL_ERROR";

    public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, CODE, message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, CODE, message, cause);
    }

    public InternalServerException(String code, String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, message, cause);
    }
}
