package com.mercadolibre.solarsystem.service;

import java.util.List;
import java.util.Optional;

import com.mercadolibre.solarsystem.model.Weather;

public interface WeatherService {
	
	List<Weather> findAll();
	Optional<Weather> findById(Integer id);
}
