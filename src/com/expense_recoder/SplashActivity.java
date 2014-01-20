package com.expense_recoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.expense_recoder.util.Constants;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(Constants.SPLASH_TIME);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					startActivity(new Intent(getApplicationContext(),HomeActivity.class));
				}
			}
		}).start();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
