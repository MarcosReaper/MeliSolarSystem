package com.mercadolibre.solarsystem.service;

import com.mercadolibre.solarsystem.dto.weatherPlanetResponseDTO;

public interface WeatherPlanetService {
	
	weatherPlanetResponseDTO getWeatherByDayAndPlanetName(Integer day, String planet);
}
