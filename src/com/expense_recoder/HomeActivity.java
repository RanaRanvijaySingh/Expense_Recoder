package com.expense_recoder;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.expense_recoder.util.Constants;
import com.expense_recoder.util.LOG;

public class HomeActivity extends Activity implements OnEditorActionListener,OnClickListener {

	private static String strTitle = null;
	private static int name_id = 0;
	private static int event_id = 0;
	private EditText editTextTitle;
	private TextView textViewTitle;
	private TableManager mTableManager;
	protected String strDialogString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initializeComponents();
	}

	private void initializeComponents() {
		name_id = 0;
		event_id = 0;
		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
		textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		editTextTitle.setOnEditorActionListener(this);
		textViewTitle.setOnClickListener(this);
		mTableManager = new TableManager(this);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		String strTitle = v.getText().toString();
		if (!strTitle.equals("")) {
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
		switch (v.getId()) {
		case R.id.textViewTitle:
			textViewTitle.setVisibility(View.GONE);
			editTextTitle.setVisibility(View.VISIBLE);
			editTextTitle.setHint(strTitle);
			break;
		default:
			showDialogBox(v);
			break;
		}
	}

	private void showDialogBox(final View view) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_box);
		dialog.setTitle(getResources().getString(R.string.app_name));
		EditText editTextDialog = (EditText)dialog.findViewById(R.id.editTextDialog);
		editTextDialog.setHint(((TextView)view).getText());
		Button buttonDialog = (Button)dialog.findViewById(R.id.buttonDialogOk);
		buttonDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editTextDialog = (EditText)dialog.findViewById(R.id.editTextDialog);
				String strEditText = editTextDialog.getText().toString();
				if(strEditText.equals("")) {
					
				} else { 
					((TextView)view).setText(editTextDialog.getText().toString());
				}
				
				dialog.dismiss();

			}
		});
		dialog.show();		
	}

	public void onClickAddName(View view) {
		LOG.i("clicked", "add name " + name_id);
		TableRow tableRowName = (TableRow) findViewById(R.id.tableRowName);
		tableRowName.addView(getTextView(name_id,Constants.NAME));
		name_id++;
	}

	private TextView getTextView(int id, String name) {
		TextView textViewName = new TextView(this);
		textViewName.setId(id);
		textViewName.setPadding(10, 8, 10, 4);
		textViewName.setTextSize(Constants.TEXT_SIZE_GENERAL);
		textViewName.setHint(name);
		textViewName.setOnClickListener(this);
		return textViewName;
	}

	public void onClickSubstractName(View view) {
		if (name_id > 0) {
			name_id--;
			LOG.i("clicked", "substract name " + name_id);
			TableRow tableRowName = (TableRow) findViewById(R.id.tableRowName);
			TextView textViewName = (TextView)tableRowName.findViewById(name_id);
			tableRowName.removeView(textViewName);
		}
	}

	public void onClickAddEvent(View view) {
		LOG.i("clicked", "add event " + event_id);
		TableLayout tableLayoutEvent = (TableLayout)findViewById(R.id.tableLayoutEvent);
		TableRow tableRowEvent = new TableRow(this);
		tableRowEvent.setId(event_id);
		tableRowEvent.addView(getTextView(event_id, Constants.EVENT));
		tableLayoutEvent.addView(tableRowEvent);
		event_id++;
		mTableManager.addRow(event_id, name_id);
	}

	public void onClickSubstractEvent(View view) {
		if (event_id > 0) {
			event_id--;
			LOG.i("clicked", "substract event " + event_id);
			TableLayout tableLayoutEvent = (TableLayout)findViewById(R.id.tableLayoutEvent);
			TableRow tableRow =(TableRow) tableLayoutEvent.findViewById(event_id);
			tableLayoutEvent.removeView(tableRow);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
