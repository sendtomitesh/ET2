package com.emiexpert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ManageInterestActivity extends Activity {
	TextView tvCurInterest, tvNewInterest, tvEMI, tvPeriods, tvSaving;
	SeekBar seekBarEMI, seekBarPeriods;
	Button btnPartpayment, btnReset, btnEmailPaymentChart;
	Loan adjustedLoan;
	boolean changingEMI = false;
	boolean changingDuration = false;
	long saving = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_interest);
		setLoan();
		initializeGlobals();
		setSeekbars();

	}

	private void setSeekbars() {
		// TODO Auto-generated method stub
		seekBarEMI.setProgress((int) adjustedLoan.getCurrEMI());
		tvEMI.setText("EMI :" + adjustedLoan.getCurrEMI());
		seekBarEMI.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				changingEMI = false;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				changingEMI = true;
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				tvEMI.setText("EMI :" + progress);
				if (!changingDuration) {
					adjustedLoan.setNewEMI(progress);
					tvPeriods.setText("duration :"
							+ adjustedLoan.getCurrDuration());
					seekBarPeriods.setProgress(adjustedLoan.getmLoanDuration());
					tvNewInterest.setText("Rs."
							+ adjustedLoan.getTotalInterest());
					tvSaving.setText("You Saved Rs." + getSaving());
				}

			}
		});

		tvPeriods.setText("Periods :" + adjustedLoan.getCurrDuration());
		seekBarPeriods.setProgress(adjustedLoan.getmLoanDuration());
		seekBarPeriods
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						changingDuration = false;
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						changingDuration = true;
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {

						tvPeriods.setText("Periods :" + progress);
						if (!changingEMI) {
							adjustedLoan.setNewDuration(progress);
							tvEMI.setText("EMI :" + adjustedLoan.getCurrEMI());
							seekBarEMI.setProgress((int) adjustedLoan
									.getCurrEMI());
							tvNewInterest.setText("Rs."
									+ adjustedLoan.getTotalInterest());
							tvSaving.setText("You Saved Rs." + getSaving());
						}
					}
				});
	}

	private void setLoan() {
		// copying object to new object
		try {
			adjustedLoan = null;
			adjustedLoan = (Loan) MainActivity.mLoan.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initializeGlobals() {
		tvCurInterest = (TextView) findViewById(R.id.tvCurInterest);
		tvCurInterest.setText("Rs." + MainActivity.mLoan.getTotalInterest());
		tvNewInterest = (TextView) findViewById(R.id.tvNewInterest);
		tvSaving = (TextView) findViewById(R.id.tvSavedInterest);
		tvEMI = (TextView) findViewById(R.id.tvEMI);
		tvPeriods = (TextView) findViewById(R.id.tvPeriods);
		seekBarEMI = (SeekBar) findViewById(R.id.SeekBarEMI);
		seekBarEMI.setMax((int) adjustedLoan.getmLoanPrinciple() / 5);

		seekBarPeriods = (SeekBar) findViewById(R.id.SeekBarPeriods);

	}

	public void reset(View v) {
		setLoan();

		tvNewInterest.setText(getResources().getString(R.string.rs));
		tvSaving.setText(getResources().getString(R.string.rssaved));
		seekBarEMI.setProgress((int) adjustedLoan.getCurrEMI());
		seekBarPeriods.setProgress(adjustedLoan.getmLoanDuration());
		tvEMI.setText("EMI :" + adjustedLoan.getCurrEMI());
		tvPeriods.setText("Periods :" + adjustedLoan.getCurrDuration());

	}

	public void showInfo(View v) {
		Intent intent = new Intent(this, InfoActivity.class);
		startActivity(intent);
		finish();
	}

	public void addPartpayments(View v) {
		Intent myIntent = new Intent();
		myIntent.setClass(ManageInterestActivity.this,
				ManagePartPaymentActivity.class);
		// myIntent.putExtra("curInterest",.getTotalInterest());
		startActivity(myIntent);
		finish();
	}
	
	
	public void gotoHome(View v) {
		finish();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}
	public long getSaving() {
		long saving = MainActivity.mLoan.getTotalInterest()
				- adjustedLoan.getTotalInterest();
		if (saving <= 0) {
			saving = 0;
		}
		return saving;
	}

	

}
