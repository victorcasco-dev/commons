package com.reservas.commons.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public static final String CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, CODE, message);
    }

    public ResourceNotFoundException(String code, String message) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
}
