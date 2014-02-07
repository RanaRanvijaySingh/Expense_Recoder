package com.expense_recoder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.expense_recoder.util.LOG;

public class ExpenseManager extends Activity {
	private final String TAG = getClass().getName();
	private LinearLayout layoutData, layoutNames, layoutEvents;

	public ExpenseManager(LinearLayout linearLayoutData,LinearLayout linearLayoutName, LinearLayout linearLayoutEvent) {
		this.layoutData = linearLayoutData;
		this.layoutNames = linearLayoutName;
		this.layoutEvents = linearLayoutEvent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LOG.v(TAG, "onCreate function.");
	}
}
