package com.mercadolibre.solarsystem.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.solarsystem.model.Planet;
import com.mercadolibre.solarsystem.model.Weather;
import com.mercadolibre.solarsystem.model.WeatherPlanet;
import com.mercadolibre.solarsystem.utils.MathUtil;

@DataJpaTest
@RunWith(SpringRunner.class)
public class calculatePlanetPosition {
	
	Planet vulcano;
	Planet ferengi;
	Planet betasoide;
	Integer daysOfCalculate;
	
	@Before
	public void buildPlanets() {
		
		betasoide = buildBetasoidePlanet();
		ferengi = buildFerengiPlanet();
		vulcano = buildVulcanoPlanet();
		daysOfCalculate = 1200;
	}

	@Test
	public void droughtWeather() {
		
		for(Integer i=0;i<daysOfCalculate;i++) {
			List<WeatherPlanet> weatherPlanetList = generateWeather(i);
		}
	}
	
	private List<WeatherPlanet> generateWeather(Integer day) {
		List<WeatherPlanet> weatherPlanetList = new ArrayList<>();
		WeatherPlanet weatherPlanet = new WeatherPlanet();
		
		weatherPlanet.setDay(day);
		
		weatherPlanet.setWeather(calculateWeather(ferengi.calculatePositionInDegress(day),
				betasoide.calculatePositionInDegress(day), vulcano.calculatePositionInDegress(day)));
		
		weatherPlanetList.add(weatherPlanet);
		return weatherPlanetList;
	}

	private Weather calculateWeather(Integer degreesOfFirstPlanet, 
			Integer degreesOfSecondPlanet, Integer degreesOfThirdPlanet) {
		if(degreesOfFirstPlanet.equals(degreesOfSecondPlanet) && degreesOfSecondPlanet.equals(degreesOfThirdPlanet)) {
			
		}
		return null;
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
