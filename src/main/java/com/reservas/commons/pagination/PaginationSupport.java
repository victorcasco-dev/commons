package com.reservas.commons.pagination;

/**
 * Límites de paginación expuestos por {@code app.pagination.*} en cada microservicio.
 */
public interface PaginationSupport {

	int getDefaultSize();

	int getMaxSize();
}
