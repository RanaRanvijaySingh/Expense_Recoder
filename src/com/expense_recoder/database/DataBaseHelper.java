package com.expense_recoder.database;

import java.io.ObjectInputStream.GetField;

import com.expense_recoder.util.LOG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper  {

	private static final String TAG = GetField.class.getName();

	public static final String KEY_ROWID = "_id";
	public static final String KEY_TRIP_ID_OCCASION = "trip_id";
	public static final String KEY_TRIP_NAME = "trip_name";
	public static final String KEY_EVENT_ID_OCCASION = "event_id";
	public static final String KEY_EVENT_NAME = "event_name";

	public static final String KEY_TRIP_ID_RECORD = "trip_id";
	public static final String KEY_EVENT_ID_RECORD = "event_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_CONTRIBUTION = "contribution";

	public static final String DATABASE_TABLE_OCCASION = "occasion";
	public static final String DATABASE_TABLE_RECORD = "record";
	
	private static final String DATABASE_NAME = "expense-reporter";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE_TABLE_OCCASION = "create table "
			+ DATABASE_TABLE_OCCASION
			+ "("
			+ KEY_ROWID
			+ " integer primary key autoincrement, "
			+ KEY_TRIP_ID_OCCASION
			+ " text not null, "
			+ KEY_TRIP_NAME
			+ " text not null, "
			+ KEY_EVENT_ID_OCCASION
			+ " text, "
			+ KEY_EVENT_NAME
			+ " text "
			+ ");";

	private static final String DATABASE_CREATE_TABLE_RECORD = "create table "
			+ DATABASE_TABLE_RECORD 
			+ "(" 
			+ KEY_ROWID
			+ " integer primary key autoincrement, " 
			+ KEY_TRIP_ID_RECORD
			+ " text not null, " 
			+ KEY_EVENT_ID_RECORD 
			+ " text, " 
			+ KEY_NAME
			+ " text , " 
			+ KEY_CONTRIBUTION 
			+ " integer " 
			+ ");";
	
	public static final String OCCASION_TABLE_SELECT_ALL_ROW = "SELECT "+KEY_ROWID+" FROM "+ DATABASE_TABLE_OCCASION;
	public static final String OCCASION_TABLE_SELECT_ALL_TRIP_NAME = "SELECT DISTINCT "+KEY_TRIP_NAME+" FROM "+ DATABASE_TABLE_OCCASION;
	public static final String OCCASION_TABLE_SELECT_TRIP_NAME = "SELECT "+KEY_TRIP_NAME+" FROM "+ DATABASE_TABLE_OCCASION;
	private static final String DATABASE_UPGRADE_OCCASION = "DROP TABLE IF EXISTS "+ DATABASE_TABLE_OCCASION;
	private static final String DATABASE_UPGRADE_RECORD = "DROP TABLE IF EXISTS "	+ DATABASE_TABLE_RECORD;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_TABLE_OCCASION);
		db.execSQL(DATABASE_CREATE_TABLE_RECORD);
		Log.i(TAG, "database created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DATABASE_UPGRADE_OCCASION);
		db.execSQL(DATABASE_UPGRADE_RECORD);
		onCreate(db);
		Log.i(TAG, "database upgraded");
	}
}
