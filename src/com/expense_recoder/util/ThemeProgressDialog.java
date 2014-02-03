package com.expense_recoder.util;

import com.expense_recoder.R;
import com.expense_recoder.RecordListActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ThemeProgressDialog extends Dialog {

	private RecordListActivity  mContext;

	public ThemeProgressDialog(RecordListActivity mContext) {
		super(mContext);
		this.mContext = mContext;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.progress_dialog);
		View viewBack = this.getWindow().getDecorView();
		viewBack.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
		setCancelable(false);
	}

}
