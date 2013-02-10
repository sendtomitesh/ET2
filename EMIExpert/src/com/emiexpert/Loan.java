package com.emiexpert;

import java.util.ArrayList;

import android.util.Log;

import com.emiexpert.PartPayment.PartPaymentType;
//this 
public class Loan implements Cloneable{
	private double mLoanInterest;
	private long mLoanPrinciple;
	private int mLoanDuration;
	private ArrayList<EmiMonth> mEmiMonths;
	private ArrayList<PartPayment> mPartPayments = null;
	private long currEMI;
	public static double sInterestComponent;

	public Loan(long principle, double interest, int duration) {
		this.mLoanPrinciple = principle;
		this.mLoanInterest = interest;
		this.mLoanDuration = duration;
		mEmiMonths = new ArrayList<EmiMonth>();
		mPartPayments= new ArrayList<PartPayment>();
		setInterestComponent();
		setCurrEMI();
		generateEmis();
	}
	
	public boolean isPartPaymentInthisMonth(int month)
	{	
		if(mPartPayments.size()>0)
		{
			for (int i = 0; i < mPartPayments.size(); i++) {
				if(mPartPayments.get(i).getMonth()==month){
					return true;
				}
			}
		}
		return false;
	}
	//N
	public double getPartPaymentAmount(int month)
	{
		for(int i=0;i<mPartPayments.size();i++)
		{
			if(mPartPayments.get(i).getMonth()==month)
			{				
				return mPartPayments.get(i).getAmount();
			}			
		}
		return 0;		
	}
	//N
	public PartPaymentType getPartPaymentType(int month)
	{
		for(int i=0;i<mPartPayments.size();i++)
		{
			if(mPartPayments.get(i).getMonth()==month)
			{
				return mPartPayments.get(i).getPaymentType();
			}			
		}
		return null;		
	}
	
	public double getmLoanInterest() {
		return mLoanInterest;
	}

	public long getmLoanPrinciple() {
		return mLoanPrinciple;
	}

	public int getmLoanDuration() {
		return mLoanDuration;
	}

	private void setCurrEMI() {
			
		double divider = 1200;
		double emi = 0;
		double i = mLoanInterest / divider;
			emi = mLoanPrinciple * i * Math.pow(1 + i, mLoanDuration)
					/ (Math.pow(1 + i, mLoanDuration) - 1);
		 
		currEMI= Utility.getRoundOfDouble(emi);

				
	}
	public void setCurrEMIForLessEMI(int NoOfmonths)
	{
		double divider = 1200;
		double emi = 0;
		double i = mLoanInterest / divider;
			emi = mLoanPrinciple * i * Math.pow(1 + i, NoOfmonths)
					/ (Math.pow(1 + i, NoOfmonths) - 1);
		 
		currEMI= Utility.getRoundOfDouble(emi);

	}
	public long getEMI(int month) {
		double divider = 1200;
		double emi = 0;
		double i = mLoanInterest / divider;
		if (mPartPayments == null) {
			emi = mLoanPrinciple * i * Math.pow(1 + i, mLoanDuration)
					/ (Math.pow(1 + i, mLoanDuration) - 1);
		} else if (mPartPayments.get(month).getPaymentType() == PartPayment.PartPaymentType.LESS_EMI) {

			emi = 0.00;
		}
		return Utility.getRoundOfDouble(emi);
	}


	public void initalizePartPayment() {
		mPartPayments = new ArrayList<PartPayment>();
	}

	public void addPartPayment(PartPayment partPayment) {
		if(partPayment.getPaymentType() ==  PartPaymentType.LESS_DURATION)
		{
			//set values here
			EmiMonth emiMonth;
			long openingBal=Utility.getRoundOfDouble(mEmiMonths.get(partPayment.getMonth()-1).getOpeningBal()-partPayment.getAmount()); 
			for(int i= partPayment.getMonth();i<mLoanDuration;i++)
			{
				
				emiMonth= new EmiMonth(openingBal, currEMI);
				openingBal = emiMonth.getClosingPrinciple();
				mEmiMonths.set(i, emiMonth);
			} 
		}
		else if(partPayment.getPaymentType() == PartPaymentType.LESS_EMI)
		{
			//set values here
			
			long openingBal=mLoanPrinciple=Utility.getRoundOfDouble(mEmiMonths.get(partPayment.getMonth()-1).getClosingPrinciple()-partPayment.getAmount()) ;
			setCurrEMIForLessEMI(mLoanDuration-partPayment.getMonth());
		//	double openingBal= mEmiMonths.get(partPayment.getMonth()-1).getOpeningBal()-partPayment.getAmount();
			EmiMonth emiMonth;
			for(int i= partPayment.getMonth();i<mLoanDuration;i++)
			{
				
				emiMonth= new EmiMonth(openingBal, currEMI);
				openingBal = emiMonth.getClosingPrinciple();
				mEmiMonths.set(i, emiMonth);
			}
			
		}
		mPartPayments.add(partPayment);
	}

	public void generateEmis() {
		EmiMonth emiMonth;
		long openingBal = mLoanPrinciple;

		for (int i = 0; i < mLoanDuration; i++) {
			emiMonth = new EmiMonth(openingBal, currEMI);
			openingBal = emiMonth.getClosingPrinciple();
			mEmiMonths.add(emiMonth);
		}
	}
	public long getCurrEMI()
	{
		return currEMI;
	}
	public long getCurrDuration()
	{
		return mLoanDuration;
	}

	public void setNewEMI(long newEMI)
	{
		// set new EMI here  ;//,
		double a=newEMI-mLoanPrinciple*sInterestComponent;
		double b = newEMI/a;
		mLoanDuration= (int)Utility.logOfBase(b, sInterestComponent+1.0);
		mEmiMonths = new ArrayList<EmiMonth>();
		mPartPayments= new ArrayList<PartPayment>();
		setCurrEMI();
		generateEmis();
	}
	public void setNewDuration(int newMonths)
	{
		//set new duration here
		this.mLoanDuration = newMonths;
		mEmiMonths = new ArrayList<EmiMonth>();
		mPartPayments= new ArrayList<PartPayment>();
		
		setCurrEMI();
		generateEmis();
	}
	
	public void setInterestComponent() {
		sInterestComponent = mLoanInterest / 1200.00;
	}

	public ArrayList<EmiMonth> getAllEmis() {
		return mEmiMonths;
	}

	public EmiMonth getEmi(int month) {
		if (month <= mLoanDuration) {
			return mEmiMonths.get(month);
		} else {
			return null;
		}
	}

	public long getTotalInterest() {
		double totalPaid = 0.0;
		for (int i = 0; i < mEmiMonths.size(); i++) {
			totalPaid += Utility.getRoundOfDouble(mEmiMonths.get(i)
					.getInterest());
		}
		return Utility.getRoundOfDouble(totalPaid);
	}
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
