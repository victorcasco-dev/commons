package com.reservas.commons.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Construye {@link org.springframework.data.domain.Pageable} acotado por {@link PaginationSupport}.
 */
public class PageRequestFactory {

	private final PaginationSupport pagination;

	public PageRequestFactory(PaginationSupport pagination) {
		this.pagination = pagination;
	}

	public PageRequest from(Integer page, Integer size, Sort sort) {
		int resolvedPage = page == null ? 0 : Math.max(page, 0);
		int resolvedSize = size == null ? pagination.getDefaultSize() : size;
		if (resolvedSize < 1) {
			resolvedSize = pagination.getDefaultSize();
		}
		if (resolvedSize > pagination.getMaxSize()) {
			resolvedSize = pagination.getMaxSize();
		}
		return PageRequest.of(resolvedPage, resolvedSize, sort);
	}
}
