package com.expense_recoder;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
		Button buttonDialog = (Button)dialog.findViewById(R.id.buttonDialogOk);
		buttonDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editTextDialog = (EditText)dialog.findViewById(R.id.editTextDialog);
				((TextView)view).setText(editTextDialog.getText().toString());
				dialog.dismiss();
				
			}
		});
		dialog.show();		
	}

	public void onClickAddName(View view) {
		LOG.i("clicked", "add name " + name_id);
		TableRow tableRowName = (TableRow) findViewById(R.id.tableRowName);
		tableRowName.addView(getTextView(name_id));
		name_id++;
	}

	private TextView getTextView(int id) {
		TextView textViewName = new TextView(this);
		textViewName.setId(id);
		textViewName.setPadding(10, 8, 10, 4);
		textViewName.setTextSize(Constants.TEXT_SIZE_GENERAL);
		textViewName.setHint("Name");
		textViewName.setOnClickListener(this);
		return textViewName;
	}

	public void onClickSubstractName(View view) {
		// if (name_id > 0) {
		// name_id--;
		// LOG.i("clicked", "substract name " + name_id);
		// LinearLayout linearLayoutName = (LinearLayout)
		// findViewById(R.id.linearLayoutName);
		// TextView textViewName = (TextView)
		// linearLayoutName.findViewById(name_id);
		// linearLayoutName.removeView(textViewName);
		// }
	}

	public void onClickAddEvent(View view) {
		LOG.i("clicked", "add event " + event_id);
		// LinearLayout linearLayoutEvent = (LinearLayout)
		// findViewById(R.id.linearLayoutEvent);
		// TextView textViewEvent = new TextView(this);
		// textViewEvent.setId(event_id);
		// textViewEvent.setTextSize(Constants.TEXT_SIZE_GENERAL);
		// textViewEvent.setHint("Event");
		// linearLayoutEvent.addView(textViewEvent);
		// event_id++;
		// mTableManager.addRow(event_id, name_id);
	}

	public void onClickSubstractEvent(View view) {
		// if (event_id > 0) {
		// event_id--;
		// LOG.i("clicked", "substract event " + event_id);
		// LinearLayout linearLayoutName = (LinearLayout)
		// findViewById(R.id.linearLayoutEvent);
		// TextView textViewEvent = (TextView)
		// linearLayoutName.findViewById(event_id);
		// linearLayoutName.removeView(textViewEvent);
		// }
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
