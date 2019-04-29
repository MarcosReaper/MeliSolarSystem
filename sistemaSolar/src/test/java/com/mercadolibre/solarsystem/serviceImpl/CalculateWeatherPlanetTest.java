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
public class CalculateWeatherPlanetTest {
	
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
	public void droughtWeather() {
		
		for(Integer i=0;i<daysOfCalculate;i++) {
			weatherPlanetList.addAll(generateWeather(i));
		}
		weatherPlanetList.stream().forEach(weatherPlanet -> {
			System.out.println(weatherPlanet.toString());
		});
	}
	
	private List<WeatherPlanet> generateWeather(Integer day) {
		List<WeatherPlanet> weatherPlanetList = new ArrayList<>();
		
		Weather weather = calculateWeather(ferengi.calculatePositionInDegress(day),
				betasoide.calculatePositionInDegress(day), vulcano.calculatePositionInDegress(day));
		
		WeatherPlanet weatherPlanetFerengi = new WeatherPlanet();
		weatherPlanetFerengi.setDay(ferengi.getDayOfTheYear(day));
		weatherPlanetFerengi.setYear(ferengi.getYear(day));
		weatherPlanetFerengi.setWeather(weather);
		weatherPlanetFerengi.setPlanet(ferengi);
		
		WeatherPlanet weatherPlanetBetasoide = new WeatherPlanet();
		weatherPlanetBetasoide.setDay(betasoide.getDayOfTheYear(day));
		weatherPlanetBetasoide.setYear(betasoide.getYear(day));
		weatherPlanetBetasoide.setWeather(weather);
		weatherPlanetBetasoide.setPlanet(betasoide);
		
		WeatherPlanet weatherPlanetVulcano = new WeatherPlanet();
		weatherPlanetVulcano.setDay(vulcano.getDayOfTheYear(day));
		weatherPlanetVulcano.setYear(vulcano.getYear(day));
		weatherPlanetVulcano.setWeather(weather);
		weatherPlanetVulcano.setPlanet(vulcano);
		
		if(weather!=null) {
			
			weatherPlanetList.add(weatherPlanetVulcano);
			weatherPlanetList.add(weatherPlanetBetasoide);
			weatherPlanetList.add(weatherPlanetFerengi);
		}
		
		return weatherPlanetList;
	}

	private Weather calculateWeather(Integer degreesOfFirstPlanet, 
			Integer degreesOfSecondPlanet, Integer degreesOfThirdPlanet) {
		Weather weather = null;
		
		if(isDrought(degreesOfFirstPlanet,degreesOfSecondPlanet,degreesOfThirdPlanet)) {
			weather = new Weather();
			weather.setId(1);
			weather.setDescription("Sequia");
		}
		return weather;
	}

	private boolean isDrought(Integer degreesOfFirstPlanet, 
			Integer degreesOfSecondPlanet, Integer degreesOfThirdPlanet) {
		
		return (degreesOfFirstPlanet.equals(degreesOfSecondPlanet) && degreesOfSecondPlanet.equals(degreesOfThirdPlanet)) ||
				(MathUtil.isOppositeDegree(degreesOfFirstPlanet, degreesOfSecondPlanet) 
						&& degreesOfFirstPlanet.equals(degreesOfThirdPlanet) || MathUtil.isOppositeDegree(degreesOfFirstPlanet, degreesOfThirdPlanet) 
						&& degreesOfFirstPlanet.equals(degreesOfSecondPlanet) || MathUtil.isOppositeDegree(degreesOfFirstPlanet, degreesOfThirdPlanet)
						&& degreesOfThirdPlanet.equals(degreesOfSecondPlanet));
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
