package com.expense_recoder.database;

import java.io.ObjectInputStream.GetField;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.expense_recoder.util.LOG;

public class DatabaseOperation {
	
	private static final String TAG = GetField.class.getName();
	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;
	
	public DatabaseOperation(Context context,String tableName, String [] names, String [] events) {
		dbHelper = new DataBaseHelper(context,tableName, names, events);
		open();
	}
	
	private void open() {
		database = dbHelper.getWritableDatabase();
		LOG.i(TAG, "database opened");
	}
	
	public void close() {
		dbHelper.close();
		LOG.i(TAG, "database closed");
	}

	public void insertIntoTable(String[] errorRecord) {
		//instert operation
		LOG.i(TAG, "data inserted");
		close();
	}

	public void deleteFromTable(long id) {
		database.delete(DataBaseHelper.DATABASE_TABLE, DataBaseHelper.KEY_ROW_ID+ " = " + id, null);
	}
	
}
