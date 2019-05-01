package com.mercadolibre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WeatherPlanet {
	
	@Id
	private Long id;
	
	@Column
	private Integer day;
	
	@Column
	private Integer year;

	@OneToOne(fetch = FetchType.LAZY)
	private Weather weather;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Planet planet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		if(weather!=null && planet!=null) {
			return "WeatherPlanet [id=" + id + ", day=" + day + ", year=" + year + ", weather=" + weather.getDescription() + ", planet="
					+ planet.getName() + "]";
		}else {
			return "WeatherPlanet [id=" + id + ", day=" + day + ", year=" + year + ", weather=" + weather + ", planet="
					+ planet + "]";
		}
		
	}
	
	
}
