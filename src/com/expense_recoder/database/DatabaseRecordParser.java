package com.expense_recoder.database;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import com.exponse_recoder.model.OccasionModel;
import com.exponse_recoder.model.RecordModel;

public class DatabaseRecordParser {
	private final String TAG = getClass().getName();
	
	public List<OccasionModel> getOccasionData(Cursor mCursor) {
		List<OccasionModel> listOccasions = new ArrayList<OccasionModel>();
		while(mCursor.moveToNext()) {
			OccasionModel mOccasionModel = new OccasionModel();
			mOccasionModel.setTripId(mCursor.getString(0));
			mOccasionModel.setTripName(mCursor.getString(1));
			if (mCursor.getString(2)==null) {
				mOccasionModel.setEventId("");
			} else {
				mOccasionModel.setEventId(mCursor.getString(2));
			}
			if (mCursor.getString(3)==null) {
				mOccasionModel.setEventName("");
			} else {
				mOccasionModel.setEventName(mCursor.getString(3));
			}
			listOccasions.add(mOccasionModel);
		}
		return listOccasions;
	}

	public RecordModel getRecordData(Cursor mCursor) {
		RecordModel mRecordModel = new RecordModel();
		mRecordModel.setTripId(mCursor.getString(0));
		mRecordModel.setEventId(mCursor.getString(1));
		
		if (mCursor.getString(3)==null) {
			mRecordModel.setName("");
		} else {
			mRecordModel.setName(mCursor.getString(2));
		}
		if (mCursor.getString(3)==null) {
			mRecordModel.setContribution("");
		} else {
			mRecordModel.setContribution(mCursor.getString(3));
		}
		return mRecordModel;
	}

}
