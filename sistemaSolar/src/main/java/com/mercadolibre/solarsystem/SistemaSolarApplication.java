package com.mercadolibre.solarsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"com.mercadolibre.solarsystem.repository","com.mercadolibre.solarsystem.model"})
@EnableTransactionManagement
@EnableJpaRepositories("com.mercadolibre.solarsystem.repository")
public class SistemaSolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaSolarApplication.class, args);
	}

}
