package com.expense_recoder.database;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.expense_recoder.util.LOG;
import com.exponse_recoder.model.OccasionModel;

public class DatabaseOperation {
	
	private static final String TAG = GetField.class.getName();
	
	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;
	private String [] occasionAllColumn = {	DataBaseHelper.KEY_OCCASION_TRIP_ID,
			DataBaseHelper.KEY_OCCASION_TRIP_NAME,
			DataBaseHelper.KEY_OCCASION_EVENT_ID,
			DataBaseHelper.KEY_OCCASION_EVENT_NAME}; 
	
	private String [] recordAllColumn = {DataBaseHelper.KEY_RECORD_TRIP_ID,
			DataBaseHelper.KEY_RECORD_EVENT_ID,
			DataBaseHelper.KEY_RECORD_NAME,
			DataBaseHelper.KEY_RECORD_CONTRIBUTION}; 
	
 	public DatabaseOperation(Context context) {
		dbHelper = new DataBaseHelper(context);
		open();
	}

	public void open() {
		database = dbHelper.getWritableDatabase();
		Log.i(TAG, "database opened");
	}

	public void close() {
		dbHelper.close();
		Log.i(TAG, "database closed");
	}
	
	public int getLastIdFromOccasion() {
		Cursor mCursor = database.rawQuery(DataBaseHelper.OCCASION_TABLE_SELECT_ALL_ROW, null);
		if(mCursor.getCount()==0)
			return 1;
		else {
			mCursor.moveToLast();
			return mCursor.getInt(0)+1; 
		}
	}

	public void insertIntoTableOccasion(String[] occasions) { 
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.KEY_OCCASION_TRIP_ID, occasions[0]);
		values.put(DataBaseHelper.KEY_OCCASION_TRIP_NAME, occasions[1]); 
		values.put(DataBaseHelper.KEY_OCCASION_EVENT_ID, occasions[2]); 
		values.put(DataBaseHelper.KEY_OCCASION_EVENT_NAME, occasions[3]);
		long insertId = database.insert(DataBaseHelper.DATABASE_TABLE_OCCASION, null,values);
		Log.i(TAG, "data inserted into occasion table");
	}
	
	public void insertIntoTableRecord(String[] records) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.KEY_RECORD_TRIP_ID, records[0]);
		values.put(DataBaseHelper.KEY_RECORD_EVENT_ID, records[1]);
		values.put(DataBaseHelper.KEY_RECORD_NAME, records[2]);
		values.put(DataBaseHelper.KEY_RECORD_CONTRIBUTION, records[3]);
		long insertId = database.insert(DataBaseHelper.DATABASE_TABLE_RECORD, null,values);
		Log.i(TAG, "data inserted into record table");
	}

	public boolean isTableAlreadyCreated(String strTableName) {
		Cursor mCursor = database.rawQuery(DataBaseHelper.OCCASION_TABLE_SELECT_TRIP_NAME,null);
		if(mCursor == null) {
			return false;
		} else {
			while(mCursor.moveToNext()) {
				if (strTableName.equals(mCursor.getString(0))) {
					return true;
				}
			}
			return false;
		}
	}

	public String[] getAllRowsWithTripName(String strTableName,String strTripName) {
		String [] tableColumn = {DataBaseHelper.KEY_OCCASION_TRIP_ID};
		String whereClause = DataBaseHelper.KEY_OCCASION_TRIP_NAME +" = ?";
		Cursor mCursor = database.query(DataBaseHelper.DATABASE_TABLE_OCCASION, 
				tableColumn, whereClause,new String[]{strTripName}, null	, null,null);
		String [] strArrayTripIds = new String [mCursor.getCount()];
		int i=0;
		while(mCursor.moveToNext()) {
			strArrayTripIds[i++] = mCursor.getString(0);
		}
		LOG.v(TAG,"Returning all trip names.");
		return strArrayTripIds;
	}

	public void deleteAllRowWithTripId(String string) {
		String whereClauseOccasion = DataBaseHelper.KEY_OCCASION_TRIP_ID+" = ?";
		String whereClauseRecord= DataBaseHelper.KEY_RECORD_TRIP_ID+" = ?";
		database.delete(DataBaseHelper.DATABASE_TABLE_OCCASION, whereClauseOccasion, new String []{string});
		database.delete(DataBaseHelper.DATABASE_TABLE_RECORD, whereClauseRecord, new String []{string});
	}
	
	public String [] getAllDistinctTripName() {
		Cursor mCursor = database.rawQuery(DataBaseHelper.OCCASION_TABLE_SELECT_ALL_TRIP_NAME, null);
		if (mCursor != null) {
			String [] strArrayTripNames = new String [mCursor.getCount()];
			int i=0;
			while (mCursor.moveToNext()) {
				strArrayTripNames[i++] = mCursor.getString(0);
			}
			return strArrayTripNames;
		} else {
			return null;
		}
	}
	
	public List<OccasionModel> getAllRows(String strTable, String strColumn) {
		String whereClause = DataBaseHelper.KEY_OCCASION_TRIP_NAME+ " = ? ";
		Cursor mCursor = database.query(strTable, occasionAllColumn, whereClause, new String[]{strColumn}, null,null,null);
		if(mCursor == null) {
			return null;
		} else {
			DatabaseRecordParser mParser = new DatabaseRecordParser();
			List<OccasionModel> listOccasions = mParser.getOccasionData(mCursor);
			return listOccasions;
		}
	}
}

