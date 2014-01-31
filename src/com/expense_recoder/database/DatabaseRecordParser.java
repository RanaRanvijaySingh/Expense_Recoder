package com.expense_recoder.database;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import com.exponse_recoder.model.OccasionModel;

public class DatabaseRecordParser {
	private final String TAG = getClass().getName();
	
	public List<OccasionModel> getOccasionData(Cursor mCursor) {
		List<OccasionModel> listOccasions = new ArrayList<OccasionModel>();
		while(mCursor.moveToNext()) {
			OccasionModel mOccasionModel = new OccasionModel();
			mOccasionModel.setTripId(mCursor.getString(0));
			mOccasionModel.setTripName(mCursor.getString(1));
			if (mCursor.getString(2)!=null) {
				mOccasionModel.setTripName("");
			} else {
				mOccasionModel.setTripName(mCursor.getString(2));
			}
			if (mCursor.getString(3)!=null) {
				mOccasionModel.setTripName("");
			} else {
				mOccasionModel.setTripName(mCursor.getString(3));
			}
			listOccasions.add(mOccasionModel);
		}
		return listOccasions;
	}

}
