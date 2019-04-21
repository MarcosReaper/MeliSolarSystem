package com.mercadolibre.solarsystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "3")
public class Vulcano extends Planet{
	
	private final Long distanceOfTheSun = 1000L;
	private final Integer daysOfTheYear = 360 / 5;
	private final Boolean clockwise = false;
	
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
