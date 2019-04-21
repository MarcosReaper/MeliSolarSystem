package com.mercadolibre.solarsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.solarsystem.SistemaSolarApplicationTests;
import com.mercadolibre.solarsystem.config.H2TestProfileJPAConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		SistemaSolarApplicationTests.class, 
  H2TestProfileJPAConfig.class})
public class PlanetRepositoryTest {
	
	@Autowired
	private PlanetRepository planetRepository;

	@Test
	public void testConnection() {
		
	}
}
