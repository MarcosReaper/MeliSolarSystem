package com.mercadolibre.solarsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.solarsystem.model.WeatherType;

@Repository
public interface WheaterTypeRepository extends CrudRepository <WeatherType, Integer>{

}
