package com.reservas.commons.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {

    public static final String CODE = "CONFLICT";

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, CODE, message);
    }

    public ConflictException(String code, String message) {
        super(HttpStatus.CONFLICT, code, message);
    }
}
