package com.expense_recoder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.expense_recoder.database.DatabaseOperation;
import com.expense_recoder.util.LOG;

public class RecordListAdapter extends BaseAdapter{
	Context mContext;
	String [] strArrayTripNames;
	public RecordListAdapter(RecordListActivity recordListActivity) {
		this.mContext = recordListActivity;
		DatabaseOperation mDatabaseOperation = new DatabaseOperation(mContext);
		if(mDatabaseOperation.getAllDistinctTripName()!=null) {
			strArrayTripNames = mDatabaseOperation.getAllDistinctTripName();
			LOG.v("adapter","names reciived");
		}
	}

	@Override
	public int getCount() {
		return strArrayTripNames.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater mInflater = LayoutInflater.from(mContext);
		convertView = mInflater.inflate(R.layout.record_list_element, null);
		TextView textViewRecordElement = (TextView)convertView.findViewById(R.id.textViewRecordElement);
		textViewRecordElement.setText(strArrayTripNames[position]);
		return convertView;
	}

}
