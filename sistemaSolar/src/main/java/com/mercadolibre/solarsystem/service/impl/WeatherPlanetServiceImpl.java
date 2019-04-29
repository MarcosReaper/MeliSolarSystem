package com.mercadolibre.solarsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercadolibre.solarsystem.dto.weatherPlanetResponseDTO;
import com.mercadolibre.solarsystem.model.Planet;
import com.mercadolibre.solarsystem.model.WeatherPlanet;
import com.mercadolibre.solarsystem.repository.PlanetRepository;
import com.mercadolibre.solarsystem.repository.WeatherPlanetRepository;
import com.mercadolibre.solarsystem.service.WeatherPlanetService;

public class WeatherPlanetServiceImpl implements WeatherPlanetService{
	
	@Autowired
	WeatherPlanetRepository WeatherPlanetRepository;
	
	@Autowired
	PlanetRepository planetRepository;

	@Override
	public weatherPlanetResponseDTO getWeatherByDayAndPlanetName(Integer day, String planetName) {
		
		weatherPlanetResponseDTO dto = new weatherPlanetResponseDTO();
		dto.setDay(day);
		Planet planet = planetRepository.findByName(planetName);
		WeatherPlanet weatherPlanet = WeatherPlanetRepository.findByDayAndPlanet_id(day, planet.getId());
		dto.setWeather(weatherPlanet.getWeather().getDescription());
		return dto;
	}

}
