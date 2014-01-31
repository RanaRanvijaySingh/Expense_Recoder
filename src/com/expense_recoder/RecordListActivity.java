package com.expense_recoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.expense_recoder.adapter.RecordListAdapter;
import com.expense_recoder.util.Constants;

public class RecordListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_list);
	}
	
	public void onClickCreateNewRecord(View view) {
		startActivity(new Intent(this,CreateTripRecordActivity.class));
	}
	
	private void setListView() {
		final ListView listViewRecord = (ListView) findViewById(R.id.listViewRecord);
		RecordListAdapter mAdapter = new RecordListAdapter(this);
		listViewRecord.setAdapter(mAdapter);
		listViewRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
				LinearLayout layout = (LinearLayout)view;
				TextView mTextView = (TextView)layout.getChildAt(0);
				String strSelectedTrip = mTextView.getText().toString();
				Intent intent = new Intent(getApplicationContext(),CreateTripRecordActivity.class);
				intent.putExtra(Constants.SELECTED_TRIP, strSelectedTrip);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		setListView();
	}
}
