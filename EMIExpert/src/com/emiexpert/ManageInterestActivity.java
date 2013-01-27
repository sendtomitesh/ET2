package com.emiexpert;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ManageInterestActivity extends Activity {
TextView tvCurInterest,tvNewInterest,tvEMI,tvPeriods;
SeekBar seekBarEMI,seekBarPeriods;
Button btnPartpayment,btnReset,btnEmailPaymentChart;

Loan tLoan;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_interest);
		initializeGlobals();
		
		seekBarEMI.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				progress=progress*5;
				tvEMI.setText("EMI :" + progress);
				
			}
		});
		
		seekBarPeriods.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				tvPeriods.setText("Periods :" + progress);
				
			}
		});
	}
	private void initializeGlobals() {
		tvCurInterest=(TextView)findViewById(R.id.tvCurInterest);
		tvCurInterest.setText("Rs." + MainActivity.mLoan.getTotalInterest());
		tvNewInterest=(TextView)findViewById(R.id.tvNewInterest);
		tvEMI=(TextView)findViewById(R.id.tvEMI);
		tvPeriods=(TextView)findViewById(R.id.tvPeriods);
		seekBarEMI=(SeekBar)findViewById(R.id.SeekBarEMI);
		seekBarPeriods=(SeekBar)findViewById(R.id.SeekBarPeriods);		
	}
	
	public void reset(View v) {
		tvCurInterest.setText("Rs.0");
		tvNewInterest.setText("Rs.0");
		seekBarEMI.setProgress(0);
		seekBarPeriods.setProgress(0);
		tvEMI.setText("Adjust EMI");
		tvPeriods.setText("Adjust Period");
	}
	public void addPartpayment(View v)
	{
		Intent myIntent = new Intent();
		myIntent.setClass(ManageInterestActivity.this, ManagePartPaymentActivity.class);
		 //myIntent.putExtra("curInterest",.getTotalInterest());
		startActivity(myIntent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
