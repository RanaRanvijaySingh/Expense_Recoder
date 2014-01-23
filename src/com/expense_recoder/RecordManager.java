package com.expense_recoder;

import android.widget.EditText;
import android.widget.LinearLayout;

import com.expense_recoder.util.Constants;
import com.expense_recoder.util.LOG;

public class RecordManager {

	HomeActivity mContext;
	private LinearLayout linearLayoutData;
	private int existingRow = 0;
	private int existingColumn = 0;

	public RecordManager(HomeActivity homeActivity) {
		this.mContext = homeActivity;
		linearLayoutData = (LinearLayout) mContext.findViewById(R.id.linearLayoutData);
	}

	public void addEntry(int numberOfRow, int numberOfColumn) {
		if(Constants.IS_FIRST_TIME && numberOfColumn > 0 && numberOfRow > 0 ) {
			LOG.v("RecordManager", "for the first time");
			for (int i = 0; i < numberOfColumn; i++) {
				LinearLayout linearLayoutColumn = new LinearLayout(mContext);
				linearLayoutColumn.setOrientation(LinearLayout.VERTICAL);
				linearLayoutColumn.setId(numberOfColumn);
				for (int j = 0; j < numberOfRow; j++) {
					linearLayoutColumn.addView(addField());
				}
				linearLayoutData.addView(linearLayoutColumn);
				Constants.IS_FIRST_TIME = false;
				setExistingRowColumnValue(numberOfRow,numberOfColumn);
			}
		} else if( numberOfColumn > 0 && numberOfRow > 0 ){ 
			if( numberOfRow-existingRow > 0 ) {
				LOG.v("RecordManager", "additional row added");
				for (int i = 0; i < numberOfColumn; i++) {
					LinearLayout linearLayoutColumn = (LinearLayout)linearLayoutData.getChildAt(i);
					linearLayoutColumn.addView(addField());
				}
				setExistingRowColumnValue(numberOfRow,numberOfColumn);
			} else if (  numberOfColumn-existingColumn > 0 ) {
				LOG.v("RecordManager", "additional column added");
				LinearLayout linearLayoutColumn = new LinearLayout(mContext);
				linearLayoutColumn.setOrientation(LinearLayout.VERTICAL);
				linearLayoutColumn.setId(numberOfColumn);
				for (int i = 0; i < numberOfRow; i++) {
					linearLayoutColumn.addView(addField());
				}
				linearLayoutData.addView(linearLayoutColumn);
				setExistingRowColumnValue(numberOfRow,numberOfColumn);
			}
		}
	}

	private void setExistingRowColumnValue(int numberOfRow, int numberOfColumn) {
		existingRow = numberOfRow;
		existingColumn = numberOfColumn;		
	}

	private EditText addField() {
		EditText editText = new EditText(mContext);
		editText.setTextSize(Constants.TEXT_SIZE_GENERAL);
		editText.setText("     ");
		return editText;
	}

}
