package com.mercadolibre.solarsystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class Ferengi extends Planet{
	
	private final Long distanceOfTheSun = 500L;
	private final Integer daysOfTheYear = 360 / 1;
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
