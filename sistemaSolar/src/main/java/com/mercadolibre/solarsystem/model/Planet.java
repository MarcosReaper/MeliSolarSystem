package com.mercadolibre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.mercadolibre.solarsystem.utils.MathUtil;

@Entity
public class Planet {
	
	@Id
	private Integer id;
	@Column
	private String name;
	@Column
	private Long distanceOfTheSun;
	@Column
	private Integer daysOfTheYear;
	@Column
	private Boolean clockwise;
	@Column
	private Integer velocityInDegrees;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDistanceOfTheSun() {
		return distanceOfTheSun;
	}
	public void setDistanceOfTheSun(Long distanceOfTheSun) {
		this.distanceOfTheSun = distanceOfTheSun;
	}
	public Integer getDaysOfTheYear() {
		return daysOfTheYear;
	}
	public void setDaysOfTheYear(Integer daysOfTheYear) {
		this.daysOfTheYear = daysOfTheYear;
	}
	public Boolean getClockwise() {
		return clockwise;
	}
	public void setClockwise(Boolean clockwise) {
		this.clockwise = clockwise;
	}
	public Integer getVelocityInDegrees() {
		return velocityInDegrees;
	}
	public void setVelocityInDegrees(Integer velocityInDegrees) {
		this.velocityInDegrees = velocityInDegrees;
	}
	
	public Integer calculatePositionInDegress(Integer day) {
		Integer degrees = 0;
		Integer auxDay = new Integer(day);
		if(day > this.daysOfTheYear) {
			auxDay = MathUtil.restYearsFromDays(auxDay,this.daysOfTheYear);
			degrees = auxDay * this.velocityInDegrees;
		}else {
			degrees = auxDay * this.velocityInDegrees;
		}
		return degrees;
	}
	
}
