package com.reservas.commons.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {

    public static final String CODE = "UNAUTHORIZED";

    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, CODE, message);
    }

    public UnauthorizedException(String code, String message) {
        super(HttpStatus.UNAUTHORIZED, code, message);
    }
}
