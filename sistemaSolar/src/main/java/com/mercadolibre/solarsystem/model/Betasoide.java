package com.mercadolibre.solarsystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class Betasoide extends Planet{

	private final Long distanceOfTheSun = 2000L;
	private final Integer daysOfTheYear = 360 / 3;
	private final Boolean clockwise = true;
	
	@Override
	public Long getDistanceOfTheSun() {
		return distanceOfTheSun;
	}

	@Override
	public Integer getDaysOfTheYear() {
		return daysOfTheYear;
	}

	@Override
	public Boolean getClockwise() {
		return clockwise;
	}


}
