package com.expense_recoder.database;

import java.io.ObjectInputStream.GetField;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expense_recoder.util.LOG;

public class DatabaseOperation {
	
	private static final String TAG = GetField.class.getName();

	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;
	
	private String[] allColumns = {DataBaseHelper.KEY_ROWID, DataBaseHelper.KEY_TRIP_ID_OCCASION, DataBaseHelper.KEY_TRIP_NAME,
			DataBaseHelper.KEY_EVENT_ID_OCCASION,DataBaseHelper.KEY_EVENT_NAME };

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
		values.put(DataBaseHelper.KEY_TRIP_ID_OCCASION, occasions[0]);
		values.put(DataBaseHelper.KEY_TRIP_NAME, occasions[1]); 
		values.put(DataBaseHelper.KEY_EVENT_ID_OCCASION, occasions[2]); 
		values.put(DataBaseHelper.KEY_EVENT_NAME, occasions[3]);
		long insertId = database.insert(DataBaseHelper.DATABASE_TABLE_OCCASION, null,values);
		Log.i(TAG, "data inserted into occasion table");
	}
	
	public void insertIntoTableRecord(String[] records) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.KEY_TRIP_ID_RECORD, records[0]);
		values.put(DataBaseHelper.KEY_EVENT_ID_RECORD, records[1]);
		values.put(DataBaseHelper.KEY_NAME, records[2]);
		values.put(DataBaseHelper.KEY_CONTRIBUTION, records[3]);
		long insertId = database.insert(DataBaseHelper.DATABASE_TABLE_RECORD, null,values);
		Log.i(TAG, "data inserted into record table");
	}

//	public void deleteFromTable(long id) {
//		database.delete(DataBaseHelper.DATABASE_TABLE, DataBaseHelper.KEY_ROWID
//				+ " = " + id, null);
//	}

}

