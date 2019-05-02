package com.mercadolibre.solarsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mercadolibre.solarsystem.model.Weather;
import com.mercadolibre.solarsystem.repository.WeatherRepository;
import com.mercadolibre.solarsystem.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Autowired
	WeatherRepository weatherRepository;

	@Override
	public List<Weather> findAll() {
		return Lists.newArrayList(weatherRepository.findAll());
	}

	@Override
	public Optional<Weather> findById(Integer id) {
		return weatherRepository.findById(id);
	}

}
