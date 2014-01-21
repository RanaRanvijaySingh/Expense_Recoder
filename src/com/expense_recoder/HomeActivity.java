package com.expense_recoder;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.expense_recoder.util.LOG;

public class HomeActivity extends Activity implements OnEditorActionListener,
		OnClickListener {

	private static String strTitle = null;
	private static int name_id = 0;
	private static int event_id = 0;
	private EditText editTextTitle;
	private TextView textViewTitle;
	private TableManager mTableManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initializeComponents();
	}

	private void initializeComponents() {
		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
		textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		editTextTitle.setOnEditorActionListener(this);
		textViewTitle.setOnClickListener(this);
		mTableManager = new TableManager(this);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		String strTitle = v.getText().toString();
		if(!strTitle.equals("")) {
			setTitleAsTextView(strTitle);			
		} else {
			strTitle = "No title";
			setTitleAsTextView(strTitle);	
		}
		return true;
	}

	private void setTitleAsTextView(String strTitle) {
		textViewTitle.setText(strTitle);
		textViewTitle.setVisibility(View.VISIBLE);
		editTextTitle.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		textViewTitle.setVisibility(View.GONE);
		editTextTitle.setVisibility(View.VISIBLE);
		editTextTitle.setHint(strTitle);
	}
	
	public void onClickAddName(View view) {
		LOG.i("clicked", "add name " + name_id);
		LinearLayout linearLayoutName = (LinearLayout) findViewById(R.id.linearLayoutName);
		EditText editTextName = new EditText(this);
		editTextName.setSingleLine(true);
		editTextName.setId(name_id);
		editTextName.setHint("Name");
		linearLayoutName.addView(editTextName);
		name_id++;
	}

	public void onClickSubstractName(View view) {
		if (name_id > 0) {
			name_id--;
			LOG.i("clicked", "substract name " + name_id);
			LinearLayout linearLayoutName = (LinearLayout) findViewById(R.id.linearLayoutName);
			EditText editTextName = (EditText) linearLayoutName
					.findViewById(name_id);
			linearLayoutName.removeView(editTextName);
		}
	}

	public void onClickAddEvent(View view) {
		LOG.i("clicked", "add event " + event_id);
		LinearLayout linearLayoutEvent = (LinearLayout) findViewById(R.id.linearLayoutEvent);
		EditText editTextEvent = new EditText(this);
		editTextEvent.setId(event_id);
		editTextEvent.setSingleLine(true);
		editTextEvent.setHint("Event");
		linearLayoutEvent.addView(editTextEvent);
		event_id++;
		mTableManager.addRow(event_id,name_id);
	}

	public void onClickSubstractEvent(View view) {
		if (event_id > 0) {
			event_id--;
			LOG.i("clicked", "substract event " + event_id);
			LinearLayout linearLayoutName = (LinearLayout) findViewById(R.id.linearLayoutEvent);
			EditText editTextEvent = (EditText) linearLayoutName
					.findViewById(event_id);
			linearLayoutName.removeView(editTextEvent);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
