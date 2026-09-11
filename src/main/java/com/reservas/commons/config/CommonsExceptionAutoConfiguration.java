package com.reservas.commons.config;

import com.reservas.commons.exception.ApiExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnWebApplication
@Import(ApiExceptionHandler.class)
public class CommonsExceptionAutoConfiguration {
}
