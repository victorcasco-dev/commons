package com.reservas.commons;

import com.reservas.commons.config.DotenvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonsApplication {

	public static void main(String[] args) {
		DotenvLoader.load();
		SpringApplication.run(CommonsApplication.class, args);
	}

}
