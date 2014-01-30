package com.expense_recoder;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.expense_recoder.util.Constants;
import com.expense_recoder.util.LOG;

public class RecordManager {

	CreateTripRecordActivity mContext;
	private LinearLayout linearLayoutData;
	private int existingRow = 0;
	private int existingColumn = 0;

	public RecordManager(CreateTripRecordActivity mCreateTripRecordActivity) {
		this.mContext = mCreateTripRecordActivity;
		linearLayoutData = (LinearLayout) mContext.findViewById(R.id.linearLayoutData);
	}

	public void addEntry(int numberOfRow, int numberOfColumn) {
		if(Constants.IS_ADDING_FIRST_TIME && numberOfColumn > 0 && numberOfRow > 0 ) {
			LOG.v("RecordManager", "for the first time");
			for (int i = 0; i < numberOfColumn; i++) {
				LinearLayout linearLayoutColumn = createNewColumnLayoutWithId(numberOfColumn);
				for (int j = 0; j < numberOfRow; j++) {
					linearLayoutColumn.addView(addField());
				}
				linearLayoutData.addView(linearLayoutColumn);
				Constants.IS_ADDING_FIRST_TIME = false;
				setExistingRowColumnValue(numberOfRow,numberOfColumn);
			}
		} else if( numberOfColumn > 0 && numberOfRow > 0 ){ 
			if( numberOfRow > existingRow ) {
				LOG.v("RecordManager", "additional row added");
				for (int i = 0; i < numberOfColumn; i++) {
					LinearLayout linearLayoutColumn = (LinearLayout)linearLayoutData.getChildAt(i);
					linearLayoutColumn.setMinimumWidth(Constants.WIDTH);
					linearLayoutColumn.addView(addField());
				}
				setExistingRowColumnValue(numberOfRow,numberOfColumn);
			} 
			if (  numberOfColumn > existingColumn ) {
				LOG.v("RecordManager", "additional column added");
				LinearLayout linearLayoutColumn = createNewColumnLayoutWithId(numberOfColumn);
				for (int i = 0; i < numberOfRow; i++) {
					linearLayoutColumn.addView(addField());
				}
				linearLayoutData.addView(linearLayoutColumn);
				setExistingRowColumnValue(numberOfRow,numberOfColumn);
			}
		}
	}

	private LinearLayout createNewColumnLayoutWithId(int numberOfColumn) {
		LinearLayout linearLayoutColumn = new LinearLayout(mContext);
		linearLayoutColumn.setOrientation(LinearLayout.VERTICAL);
		linearLayoutColumn.setMinimumWidth(Constants.WIDTH);
		linearLayoutColumn.setId(numberOfColumn);
		return linearLayoutColumn;
	}

	private void setExistingRowColumnValue(int numberOfRow, int numberOfColumn) { 
		existingRow = numberOfRow;
		existingColumn = numberOfColumn;		
	}

	private EditText addField() {
		EditText editText = new EditText(mContext); 
		editText.setSingleLine(true);
		editText.setTextSize(Constants.EDIT_TEXT_SIZE_GENERAL);
		editText.setGravity(1);
		editText.setMaxHeight(Constants.HEIGHT_EDIT_TEXT);
		editText.setText("     ");
		return editText;
	}
 
	public void deleteEntry(int numberOfRow, int numberOfColumn) {
		if( numberOfColumn >= 0 && numberOfRow >= 0 ) {
			if( existingColumn > numberOfColumn ) {
				LOG.v("RecordManager", "removing column"+linearLayoutData.getChildCount());
				LinearLayout linearLayoutColumn = (LinearLayout)linearLayoutData.getChildAt(numberOfColumn);
				linearLayoutData.removeView(linearLayoutColumn);
				existingColumn = numberOfColumn;
				if(numberOfColumn==0) { 
					resetAllComponents();
				}
			}
			if(existingRow > numberOfRow ) {
				LOG.v("RecordManager", "removing row"+linearLayoutData.getChildCount());
				for (int i = 0; i < numberOfColumn; i++) {
					LinearLayout linearLayoutColumn = (LinearLayout)linearLayoutData.getChildAt(i);
					EditText editText = (EditText) linearLayoutColumn.getChildAt(numberOfRow);
					linearLayoutColumn.removeView(editText);
					existingRow = numberOfRow;
				}
				if(numberOfRow==0) {
					resetAllComponents();
				}
			}
		}
	}

	private void resetAllComponents() {
		Constants.IS_ADDING_FIRST_TIME = true;
		linearLayoutData.removeAllViews();
		existingColumn=0;
		existingRow=0;		
	}

	public String getElementAt(int column, int row) {
		LinearLayout mLayoutRow = (LinearLayout)linearLayoutData.getChildAt(row);
		EditText mEditText = (EditText)mLayoutRow.getChildAt(column);
		return mEditText.getText().toString();
	}

}
