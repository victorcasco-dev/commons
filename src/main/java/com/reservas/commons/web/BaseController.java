package com.reservas.commons.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * Base REST controller: logger por subclase y helpers HTTP comunes.
 */
public abstract class BaseController {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected <T> ResponseEntity<T> created(String locationPath, T body) {
		return ResponseEntity.created(URI.create(locationPath)).body(body);
	}

	protected ResponseEntity<Void> noContent() {
		return ResponseEntity.noContent().build();
	}
}
