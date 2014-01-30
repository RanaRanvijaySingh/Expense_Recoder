package com.expense_recoder.database;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.exponse_recoder.model.OccasionModel;

public class DatabaseRecordParser {

	public List<OccasionModel> getOccasionData(Cursor mCursor) {
		List<OccasionModel> listOccasions = new ArrayList<OccasionModel>();
		while(mCursor.moveToNext()) {
			
			
			
		}
		return null;
	}

}
