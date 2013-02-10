package com.emiexpert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splashscreen extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//requestWindowFeature(Window.FEATURE_NO_TITLE);
			//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.splashscreen);
			Thread sthread=new Thread()
			{
				@Override
				public void run()
				{
					try
					{
						int waited=0;
						while(waited<=3000)
						{
							sleep(100);
							waited+=100;
						}
					}catch(InterruptedException e)
					{
						
					}finally
					{
						finish();
						Intent ide = new Intent();
						ide.setClassName("com.emiexpert",
								"com.emiexpert.MainActivity");
						startActivity(ide);
					}
				}
			};
			sthread.start();
			
	}

}
