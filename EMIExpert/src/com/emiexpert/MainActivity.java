package com.emiexpert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText textPrinciple, textInterest, textDuration;
	private TextView textResult;
	public static Loan mLoan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeGlobals();
	}

	private void initializeGlobals() {
		textResult = (TextView) findViewById(R.id.text_result);
		textPrinciple = (EditText) findViewById(R.id.text_principle);
		textInterest = (EditText) findViewById(R.id.text_interest);
		textDuration = (EditText) findViewById(R.id.text_duration);
	}

	public void calculateInterest(View v) {
		if (textDuration.getText().toString() != "" && textInterest.getText().toString() != "" && textPrinciple.getText().toString() != "")
		{
			int duration = Integer.parseInt(textDuration.getText().toString());
			long principle = Long.parseLong(textPrinciple.getText().toString());
			double interest = Double.parseDouble(textInterest.getText().toString());
			mLoan = new Loan(principle, interest, duration);
			
			textResult.setText("You pay " + String.valueOf(mLoan.getTotalInterest())
					+ ".Rs of Interest");
		}
	}

	public void reset(View v) {
		textResult.setText("");
		textDuration.setText("");
		textInterest.setText("");
		textPrinciple.setText("");
	}

	public void manageInterest(View v) {

		Intent myIntent = new Intent(this, ManageInterestActivity.class);				 
		startActivity(myIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
