package com.reservas.commons.web;

import com.reservas.commons.dto.PageResponseDto;
import com.reservas.commons.pagination.PaginationSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Controllers con listados paginados ({@code createdAt} DESC, tope {@code maxSize}).
 */
public abstract class PaginatedBaseController extends BaseController {

	private final PaginationSupport pagination;

	protected PaginatedBaseController(PaginationSupport pagination) {
		this.pagination = pagination;
	}

	protected Pageable pageable(int page, int size) {
		int boundedSize = Math.min(size, pagination.getMaxSize());
		return PageRequest.of(page, boundedSize, Sort.by(Sort.Direction.DESC, "createdAt"));
	}

	protected <T> PageResponseDto<T> toResponse(Page<T> page) {
		return PageResponseDto.from(page);
	}
}
