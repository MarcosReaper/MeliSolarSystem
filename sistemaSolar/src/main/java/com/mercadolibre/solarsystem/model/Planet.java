package com.mercadolibre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WEATHER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ID_CIVILIZATION")
public abstract class Planet {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="DAY")
	private Integer day;
	
	@Column(name = "YEAR")
	private Integer year;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DESCRIPTION", referencedColumnName = "ID", insertable = false, updatable = false)
	private WeatherType weatherType;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CIVILIZATION", referencedColumnName = "ID", insertable = false, updatable = false)
	private Civilization civilization;
	
	public abstract Long getDistanceOfTheSun();

	public abstract Integer getDaysOfTheYear(); 

	public abstract Boolean getClockwise();

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public WeatherType getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(WeatherType weatherType) {
		this.weatherType = weatherType;
	}

	public Civilization getCivilization() {
		return civilization;
	}

	public void setCivilization(Civilization civilization) {
		this.civilization = civilization;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
