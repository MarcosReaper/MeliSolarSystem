package com.mercadolibre.solarsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.solarsystem.model.Civilization;

@Repository
public interface CivilizationRepository extends CrudRepository <Civilization, Integer>{
	
}
