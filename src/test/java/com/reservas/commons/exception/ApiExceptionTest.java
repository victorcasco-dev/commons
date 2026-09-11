package com.reservas.commons.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiExceptionTest {

	@Test
	void mapsCommonHttpStatuses() {
		assertEquals(HttpStatus.BAD_REQUEST, new BadRequestException("invalid").getStatus());
		assertEquals(HttpStatus.UNAUTHORIZED, new UnauthorizedException("login required").getStatus());
		assertEquals(HttpStatus.FORBIDDEN, new ForbiddenException("not allowed").getStatus());
		assertEquals(HttpStatus.NOT_FOUND, new ResourceNotFoundException("missing").getStatus());
		assertEquals(HttpStatus.CONFLICT, new ConflictException("duplicate").getStatus());
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, new UnprocessableEntityException("rules").getStatus());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, new InternalServerException("boom").getStatus());
	}

	@Test
	void domainExceptionsKeepSpecificCodes() {
		assertEquals("INVALID_OPERATION", new InvalidOperationException("cannot cancel").getCode());
		assertEquals(HttpStatus.BAD_REQUEST, new InvalidOperationException("cannot cancel").getStatus());
		assertEquals("TIME_SLOT_UNAVAILABLE", new TimeSlotUnavailableException("taken").getCode());
		assertEquals(HttpStatus.CONFLICT, new TimeSlotUnavailableException("taken").getStatus());
	}
}
