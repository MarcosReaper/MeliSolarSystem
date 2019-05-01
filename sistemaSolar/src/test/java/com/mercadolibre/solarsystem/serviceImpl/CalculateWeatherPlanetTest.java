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
		
		Weather weather = calculateWeather(day,ferengi,	betasoide, vulcano);
		
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

	private Weather calculateWeather(Integer day, Planet planet1, 
			Planet planet2, Planet planet3) {
		
		Weather weather = null;
		if(isDrought(day, planet1,planet2,planet3)) {
			weather = new Weather();
			weather.setId(1);
			weather.setDescription("Sequia");
		} else if(isRainfall(day, planet1,planet2,planet3)) {
			weather = new Weather();
			weather.setId(2);
			weather.setDescription("Precipitaciones");
		} else if(isOptimalConditions(day, planet1,planet2,planet3)) {
			weather = new Weather();
			weather.setId(3);
			weather.setDescription("Condiciones Optimas");
//		} else { 
//			weather = new Weather();
//			weather.setId(3);
//			weather.setDescription("Indefinido");
		}
		
		return weather;
	}

	private Boolean isRainfall(Integer day, Planet planet1, Planet planet2, Planet planet3) {
		
		Double triangleArea = MathUtil.area(planet1.calculateXPosition(day), planet1.calculateYPosition(day), 
				planet2.calculateXPosition(day), planet2.calculateYPosition(day), 
				planet3.calculateXPosition(day), planet3.calculateYPosition(day)); 
		
		Double triangleAreaSunWithPlanet2AndPlanet3 = MathUtil.area(0D, 0D, 
				planet2.calculateXPosition(day), planet2.calculateYPosition(day), 
				planet3.calculateXPosition(day), planet3.calculateYPosition(day));
		
		Double triangleAreaSunWithPlanet1AndPlanet3 = MathUtil.area(planet1.calculateXPosition(day), planet1.calculateYPosition(day), 
				0D, 0D,	planet3.calculateXPosition(day), planet3.calculateYPosition(day));
		
		Double triangleAreaSunWithPlanet1AndPlanet2 = MathUtil.area(planet1.calculateXPosition(day), planet1.calculateYPosition(day), 
				planet2.calculateXPosition(day), planet2.calculateYPosition(day), 0D, 0D);
		
		return (triangleArea == triangleAreaSunWithPlanet2AndPlanet3 + triangleAreaSunWithPlanet1AndPlanet3 + triangleAreaSunWithPlanet1AndPlanet2);
	}

	private Boolean isOptimalConditions(Integer day, Planet planet1, Planet planet2, Planet planet3) {
		
		Boolean isDroughtPlanets= false;
		isDroughtPlanets = MathUtil.areDotsAlligned(planet1.calculateXPosition(day), planet1.calculateYPosition(day), 
				planet2.calculateXPosition(day), planet2.calculateYPosition(day), 
				planet3.calculateXPosition(day), planet3.calculateYPosition(day));
		
		return isDroughtPlanets;
	}

	private boolean isDrought(Integer day,Planet planet1, Planet planet2, Planet planet3) {
		
		Boolean isDroughtPlanets= false;
		isDroughtPlanets = MathUtil.areDotsAlligned(planet1.calculateXPosition(day), planet1.calculateYPosition(day), 
				planet2.calculateXPosition(day), planet2.calculateYPosition(day), 
				planet3.calculateXPosition(day), planet3.calculateYPosition(day));
		
		Boolean isDroughtSun= false;
		isDroughtSun = MathUtil.areDotsAlligned(planet1.calculateXPosition(day), planet1.calculateYPosition(day), 
				planet2.calculateXPosition(day), planet2.calculateYPosition(day), 
				planet3.calculateXPosition(0), planet3.calculateYPosition(0));
		
		return (isDroughtPlanets.equals(isDroughtSun) && isDroughtPlanets.equals(true));
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
