package com.expense_recoder;

import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.expense_recoder.util.Constants;

public class TableManager {

	HomeActivity mContext;
	private View view;
	private TableLayout table;
	private int row;
	private int column;
	private int existingRow = 0;
	private int existingColumn = 0;

	public TableManager(HomeActivity homeActivity) {
		this.mContext = homeActivity;
		table = (TableLayout) mContext.findViewById(R.id.tableLayout);
	}

	public void addRow(int numberOfRow, int numberOfColumn) {
		if (numberOfColumn > 0) {
			setRowAndColumnValue(numberOfRow, numberOfColumn);
			existingColumn = numberOfColumn;
			existingRow = numberOfRow;
			table.setShrinkAllColumns(true);
			for (int i = 0; i < row; i++) {
				TableRow tableRow = addTableRow();
				for (int j = 0; j < column; j++) {
					tableRow.addView(addColumn());
				}
				table.addView(tableRow);
			}
		}
	}

	private void setRowAndColumnValue(int numberOfRow, int numberOfColumn) {
		if (numberOfColumn != existingColumn) {
			column = numberOfColumn - existingColumn;
		} else {
			column = numberOfColumn;
		}
		if (numberOfRow != existingRow) {
			row = numberOfRow - existingRow;
		} else {
			row = numberOfRow;
		}
		row = numberOfRow - existingRow;
	}

	private TableRow addTableRow() {
		TableRow tableRow = new TableRow(mContext);
		tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
		return tableRow;
	}

	private EditText addColumn() {
		EditText editText = new EditText(mContext);
		editText.setTextSize(Constants.TEXT_SIZE);
		editText.setText("hello");
		return editText;
	}

}
