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
		return mOpeningBal;
	}
	
	public double getInterest(){
		return mInterest;
	}
	
	public double getPrinciple(){
		return mPrinciple;
	}
	public double getEmi()
	{
		return mEmi;
	}
	public double getClosingPrinciple(){
		return (mOpeningBal - mPrinciple);
	}	
	public void calInterest(){
		
		mInterest = Utility.getRoundOfDouble(mOpeningBal*Loan.sInterestComponent);
	}
	public void calPrinciple(){
		mPrinciple = mEmi - mInterest;
	}	
}
