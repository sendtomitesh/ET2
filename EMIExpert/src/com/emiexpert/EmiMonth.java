package com.emiexpert;



public class EmiMonth {
	
	private double mOpeningBal;
	private double mInterest;
	private double mPrinciple;
	private double mEmi;
	
	public EmiMonth(double openingBal,double emi) {
		// TODO Auto-generated constructor stub
		this.mOpeningBal = openingBal;
		this.mEmi = emi;
		calInterest();
		calPrinciple();	
	}
	
	//getters
	public double getOpeningBal(){
		return Utility.getRoundOfDouble(mOpeningBal);
	}
	
	public double getInterest(){
		
		return Utility.getRoundOfDouble(mInterest);
	}
	
	public double getPrinciple(){
		return Utility.getRoundOfDouble(mPrinciple);
	}
	public double getEmi()
	{
		return Utility.getRoundOfDouble(mEmi);
	}
	public double getClosingPrinciple(){
		return Utility.getRoundOfDouble((mOpeningBal - mPrinciple)) ;
	}	
	public void calInterest(){
		
		mInterest = Utility.getRoundOfDouble(mOpeningBal*Loan.sInterestComponent);
	}
	public void calPrinciple(){
		mPrinciple = mEmi - mInterest;
	}	
}
