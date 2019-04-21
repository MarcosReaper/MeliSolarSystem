package com.mercadolibre.solarsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.mercadolibre.solarsystem.repository","com.mercadolibre.solarsystem.model"})
public class SistemaSolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaSolarApplication.class, args);
	}

}
