package com.expense_recoder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RecordListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listViewRecord = (ListView)findViewById(R.id.listViewRecord);
		RecordListAdapter mAdapter = new RecordListAdapter(this	);
		listViewRecord.setAdapter(mAdapter);
	}
}
