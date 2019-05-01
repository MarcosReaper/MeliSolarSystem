package com.mercadolibre.solarsystem.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.solarsystem.model.Planet;
import com.mercadolibre.solarsystem.model.WeatherPlanet;
import com.mercadolibre.solarsystem.utils.MathUtil;

@RunWith(SpringRunner.class)
public class weatherTest {
	
	private Planet vulcano;
	private Planet ferengi;
	private Planet betasoide;
	private Integer daysOfCalculate;
	List<WeatherPlanet> weatherPlanetList;
	
	@Before
	public void buildPlanets() {
		
		betasoide = buildBetasoidePlanet();
		ferengi = buildFerengiPlanet();
		vulcano = buildVulcanoPlanet();
		daysOfCalculate = 1200;
		weatherPlanetList = new ArrayList<>();
	}

	@Test
	public void alingDots() {
		Integer days = 0;
		
		while(!MathUtil.areDotsAlligned(ferengi.calculateXPosition(days), ferengi.calculateYPosition(days), 
				 betasoide.calculateXPosition(days), betasoide.calculateYPosition(days), 
					vulcano.calculateXPosition(days), vulcano.calculateYPosition(days))) {
			days++;
			System.out.println(days);
		}
		System.out.println(" --EXITO-- " + days);
	}
	
	
	private Planet buildBetasoidePlanet() {
		Planet planet = new Planet();
		planet.setId(2);
		planet.setName("Betasoide");
		planet.setClockwise(true);
		planet.setVelocityInDegrees(3);
		planet.setDaysOfTheYear(360/3);
		planet.setDistanceOfTheSun(2000L);
		return planet;
	}

	private Planet buildFerengiPlanet() {
		Planet planet = new Planet();
		planet.setId(1);
		planet.setName("Ferengi");
		planet.setClockwise(true);
		planet.setVelocityInDegrees(1);
		planet.setDaysOfTheYear(360/1);
		planet.setDistanceOfTheSun(500L);
		return planet;
	}

	private Planet buildVulcanoPlanet() {
		
		Planet planet = new Planet();
		planet.setId(3);
		planet.setName("Vulcano");
		planet.setClockwise(false);
		planet.setVelocityInDegrees(5);
		planet.setDaysOfTheYear(360/5);
		planet.setDistanceOfTheSun(1000L);
		return planet;
	}
}
