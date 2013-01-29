package com.emiexpert;



public class EmiMonth {
	
	private long mOpeningBal;
	private long mInterest;
	private long mPrinciple;
	private long mEmi;
	
	public EmiMonth(long openingBal,long emi) {
		// TODO Auto-generated constructor stub
		this.mOpeningBal = openingBal;
		this.mEmi = emi;
		calInterest();
		calPrinciple();	
	}
	
	//getters
	public long getOpeningBal(){
		return Utility.getRoundOfDouble(mOpeningBal);
	}
	
	public long getInterest(){
		
		return Utility.getRoundOfDouble(mInterest);
	}
	
	public long getPrinciple(){
		return Utility.getRoundOfDouble(mPrinciple);
	}
	public long getEmi()
	{
		return Utility.getRoundOfDouble(mEmi);
	}
	public long getClosingPrinciple(){
		return Utility.getRoundOfDouble((mOpeningBal - mPrinciple)) ;
	}	
	public void calInterest(){
		
		mInterest = Utility.getRoundOfDouble(mOpeningBal*Loan.sInterestComponent);
	}
	public void calPrinciple(){
		mPrinciple = mEmi - mInterest;
	}	
}
