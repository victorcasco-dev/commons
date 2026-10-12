package com.reservas.commons.config;

import com.reservas.commons.pagination.PageRequestFactory;
import com.reservas.commons.pagination.PaginationSupport;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnBean(PaginationSupport.class)
public class CommonsPaginationAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	PageRequestFactory pageRequestFactory(PaginationSupport paginationSupport) {
		return new PageRequestFactory(paginationSupport);
	}
}
