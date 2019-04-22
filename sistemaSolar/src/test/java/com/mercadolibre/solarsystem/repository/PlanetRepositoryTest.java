package com.mercadolibre.solarsystem.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.solarsystem.SistemaSolarApplicationTests;
import com.mercadolibre.solarsystem.config.H2TestProfileJPAConfig;
import com.mercadolibre.solarsystem.model.Planet;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		SistemaSolarApplicationTests.class, 
  H2TestProfileJPAConfig.class})
public class PlanetRepositoryTest {
	
	@Autowired
	private PlanetRepository planetRepository;

	@Test
	public void testConnection() {
		Iterable<Planet> planets = planetRepository.findAll();
		assertNotNull(planets);
	}
}
