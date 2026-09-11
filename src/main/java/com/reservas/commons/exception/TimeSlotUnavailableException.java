package com.reservas.commons.exception;

public class TimeSlotUnavailableException extends ConflictException {

    public static final String CODE = "TIME_SLOT_UNAVAILABLE";

    public TimeSlotUnavailableException(String message) {
        super(CODE, message);
    }
}
