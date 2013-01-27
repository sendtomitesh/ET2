package com.emiexpert;



public class Utility {
	
	public static double getRoundOfDouble(double value){
		double roundValue = Math.round( value * 100.0 ) / 100.0;
		return roundValue;
		
		/*DecimalFormat df=new DecimalFormat("0.00");
		String formate = df.format(value); 
		double roundValue = 0.00;
		try {
			roundValue = (Double)df.parse(formate);
			return roundValue; 
		} catch (ParseException e) {
			return roundValue;
		}*/
		
		
	}

}
