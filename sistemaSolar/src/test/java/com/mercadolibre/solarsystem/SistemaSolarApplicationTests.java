package com.mercadolibre.solarsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan({"com.mercadolibre.solarsystem.repository","com.mercadolibre.solarsystem.model"})
@EnableTransactionManagement
@EnableJpaRepositories("com.mercadolibre.solarsystem.repository")
public class SistemaSolarApplicationTests {

	@Test
	public void contextLoads() {
	}

}
