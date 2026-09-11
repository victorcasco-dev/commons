package com.reservas.commons.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends ApiException {

    public static final String CODE = "UNPROCESSABLE_ENTITY";

    public UnprocessableEntityException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, CODE, message);
    }

    public UnprocessableEntityException(String code, String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, message);
    }
}
