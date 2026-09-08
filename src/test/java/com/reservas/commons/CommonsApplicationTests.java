package com.reservas.commons;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "DB_URL", matches = ".+")
class CommonsApplicationTests {

	@Test
	void contextLoads() {
	}

}
