package com.mercadolibre.solarsystem.utils;

public class MathUtil {

	public static int oppositeDegrees(Integer degrees) {
		int oppositeDegree = 0;
		if(degrees > 360) {
			
		}else {
			oppositeDegree = degrees;
		}
		return oppositeDegree;
	}
	
	public static int restYearsFromDays(Integer day,Integer daysOnYear) {
		if(day > daysOnYear) {
			return restYearsFromDays((day - daysOnYear), daysOnYear);
		}else {
			return day;
		}
	}

}
