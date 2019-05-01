package com.mercadolibre.solarsystem.batch.tasks;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.mercadolibre.solarsystem.enums.WeatherEnum;
import com.mercadolibre.solarsystem.model.Planet;
import com.mercadolibre.solarsystem.model.Weather;
import com.mercadolibre.solarsystem.model.WeatherPlanet;
import com.mercadolibre.solarsystem.service.PlanetService;
import com.mercadolibre.solarsystem.service.WeatherPlanetService;
import com.mercadolibre.solarsystem.utils.MathUtil;

public class GenerateWeatherForecastBatch implements Tasklet{
	
	private final Logger LOGGER = Logger.getLogger(this.getClass());
	private PlanetService planetService;
	private WeatherPlanetService weatherPlanetService;
	
	private Integer daysOfCalculate;
	private List<Planet> planets;
	private List<WeatherPlanet> weatherPlanetList;
	

	public GenerateWeatherForecastBatch(PlanetService planetService, WeatherPlanetService weatherPlanetService) {
		this.planetService = planetService;
		this.weatherPlanetService = weatherPlanetService;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		init();
		for(Integer i=0;i<=daysOfCalculate;i++) {
			LOGGER.info("Dia: " + i);
			weatherPlanetList.addAll(generateWeather(i));
		}
		
		weatherPlanetService.saveAll(weatherPlanetList);
		
		return RepeatStatus.FINISHED;
	}
	
	public void init() {
		
		planets = planetService.findAll();
		daysOfCalculate = 12000;
		weatherPlanetList = new ArrayList<>();
	}
	
	private List<WeatherPlanet> generateWeather(Integer day) {
		Integer auxDay = new Integer(day);
		List<WeatherPlanet> weatherPlanetList = new ArrayList<>();
		
		Weather weather = calculateWeather(auxDay,planets);
		
		planets.stream().forEach(planet->{
			
			if(weather!=null) {
				LOGGER.info("- Planeta: " + planet.getName() + " dia: " + auxDay + " dia del anio: " 
						+ planet.getDayOfTheYear(auxDay) + " anio: " + planet.getYear(auxDay) + " Id: " +
						Long.valueOf(planet.getId().toString() + auxDay));
				
				WeatherPlanet weatherPlanet = new WeatherPlanet();
				weatherPlanet.setDay(auxDay);
				weatherPlanet.setYear(0);
				weatherPlanet.setWeather(weather);
				weatherPlanet.setPlanet(planet);
				weatherPlanet.setId(Long.valueOf(planet.getId().toString()+ auxDay));
				weatherPlanetList.add(weatherPlanet);
			}
		});
		
		return weatherPlanetList;
	}

	private Weather calculateWeather(Integer day, List<Planet> planets) {
		LOGGER.info("calculando el clima:  ");
		
		Weather weather = null;
		if(isDrought(day,planets)) {
			LOGGER.info("    Sequia");
			weather = new Weather(WeatherEnum.DROUGHT.getId());
			
		} else if(isRainfall(day, planets)) {
			LOGGER.info("    Lluvia");
			weather = new Weather(WeatherEnum.RAINFALL.getId());
			
		} else if(isOptimalConditions(day,planets)) {
			LOGGER.info("    Condiciones Optimas");
			weather = new Weather(WeatherEnum.OPTIMAL_CONDITIONS.getId());
//		} else { 
//			LOGGER.info("    Indefinido");
//			weather = new Weather(WeatherEnum.UNDEFINED.getId());
		}
		
		return weather;
	}
	
	private Boolean isOptimalConditions(Integer day, List<Planet> planets) {
		Boolean isDroughtPlanets= false;
			if(planets.size() == 3) {
				isDroughtPlanets = MathUtil.areDotsAlligned(planets.get(0).calculateXPosition(day), planets.get(0).calculateYPosition(day), 
						planets.get(1).calculateXPosition(day), planets.get(1).calculateYPosition(day), 
						planets.get(2).calculateXPosition(day), planets.get(2).calculateYPosition(day));
			}
		return isDroughtPlanets;
	}
	
	private Boolean isRainfall(Integer day, List<Planet> planets) {
		Boolean condition = false;
		if(planets.size() == 3) {
			Double triangleArea = MathUtil.area(planets.get(0).calculateXPosition(day), planets.get(0).calculateYPosition(day), 
					planets.get(1).calculateXPosition(day), planets.get(1).calculateYPosition(day), 
					planets.get(2).calculateXPosition(day), planets.get(2).calculateYPosition(day)); 
			
			Double triangleAreaSunWithPlanet2AndPlanet3 = MathUtil.area(0D, 0D, 
					planets.get(1).calculateXPosition(day), planets.get(1).calculateYPosition(day), 
					planets.get(2).calculateXPosition(day), planets.get(2).calculateYPosition(day));
			
			Double triangleAreaSunWithPlanet1AndPlanet3 = MathUtil.area(planets.get(0).calculateXPosition(day), planets.get(0).calculateYPosition(day), 
					0D, 0D,	planets.get(2).calculateXPosition(day), planets.get(2).calculateYPosition(day));
			
			Double triangleAreaSunWithPlanet1AndPlanet2 = MathUtil.area(planets.get(0).calculateXPosition(day), planets.get(0).calculateYPosition(day), 
					planets.get(1).calculateXPosition(day), planets.get(1).calculateYPosition(day), 0D, 0D);
			
			condition = (triangleArea == triangleAreaSunWithPlanet2AndPlanet3 + triangleAreaSunWithPlanet1AndPlanet3 + triangleAreaSunWithPlanet1AndPlanet2);
		}
		return condition;
	}

	
	private boolean isDrought(Integer day, List<Planet> planets) {
		Boolean condition = false;
		if(planets.size() == 3) {
			
			Boolean isDroughtPlanets= false;
			isDroughtPlanets = MathUtil.areDotsAlligned(planets.get(0).calculateXPosition(day), planets.get(0).calculateYPosition(day), 
					planets.get(1).calculateXPosition(day), planets.get(1).calculateYPosition(day), 
					planets.get(2).calculateXPosition(day), planets.get(2).calculateYPosition(day));
			
			Boolean isDroughtSun= false;
			isDroughtSun = MathUtil.areDotsAlligned(planets.get(0).calculateXPosition(day), planets.get(0).calculateYPosition(day), 
					planets.get(1).calculateXPosition(day), planets.get(1).calculateYPosition(day), 
					planets.get(2).calculateXPosition(0), planets.get(2).calculateYPosition(0));
			
			condition =  (isDroughtPlanets.equals(isDroughtSun) && isDroughtPlanets.equals(true));
		}
		return condition;
	}



}
