package com.mercadolibre.solarsystem.utils;

public class MathUtil {

	public static int oppositeDegrees(Integer degrees) {
		
		int oppositeDegree = new Integer(degrees);
		oppositeDegree += 180;
		oppositeDegree = subtractSurplusDegress(oppositeDegree);
		return oppositeDegree;
	}
	
	public static boolean isOppositeDegree(Integer degrees, Integer oppositeDegrees) {
		Integer opposite = oppositeDegrees(degrees);
		return opposite.equals(oppositeDegrees);
	}
	
	private static Integer subtractSurplusDegress(Integer degrees) {
		if(degrees > 360) {
			return subtractSurplusDegress(degrees - 360);
		}
		return degrees;
	}

	public static int restYearsFromDays(Integer day,Integer daysOnYear) {
		if(day > daysOnYear) {
			return restYearsFromDays((day - daysOnYear), daysOnYear);
		}else {
			return day;
		}
	}

}
