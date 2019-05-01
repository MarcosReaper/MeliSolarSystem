package com.mercadolibre.solarsystem.service;

import com.mercadolibre.solarsystem.dto.weatherPlanetResponseDTO;
import com.mercadolibre.solarsystem.model.WeatherPlanet;

public interface WeatherPlanetService {
	
	weatherPlanetResponseDTO getWeatherByDayAndPlanetName(Integer day, String planet);
	WeatherPlanet save(WeatherPlanet weather);
	Iterable<WeatherPlanet> saveAll(Iterable<WeatherPlanet> weatherPlanets);
}
