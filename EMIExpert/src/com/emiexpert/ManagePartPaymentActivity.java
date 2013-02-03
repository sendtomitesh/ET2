package com.emiexpert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.emiexpert.PartPayment.PartPaymentType;

public class ManagePartPaymentActivity extends Activity {

ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_partpayment);
		initializeGlobals();
		LoadLists();
		
	}
	
	public void LoadLists()
	{
		PartPayment p = new PartPayment(12, 100000.00, PartPaymentType.LESS_EMI);
		MainActivity.mLoan.addPartPayment(p);
		if(MainActivity.mLoan.getAllEmis().size()>0)
		{
		lv.setAdapter(new EMIAdapter(getApplicationContext(), MainActivity.mLoan.getAllEmis()));
		}
	}
	
	private void initializeGlobals() {
		lv=(ListView)findViewById(R.id.lvMonthlyEMI);
	}	
	
	public void reset(View v) {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
