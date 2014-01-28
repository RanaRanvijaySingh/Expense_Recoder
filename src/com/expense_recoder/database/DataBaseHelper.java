package com.expense_recoder.database;

import java.io.ObjectInputStream.GetField;

import com.expense_recoder.util.LOG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper  {
	private static final String TAG = GetField.class.getName();
	private static final String DATABASE_NAME = "expenser-recorder";
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_TABLE = null;
	
	private static String DATABASE_CREATE;
	private static String DATABASE_UPGRADE;
	
	public static final String KEY_ROW_ID = "_id";
	
	public DataBaseHelper(Context context,String tableName, String[] names,String[] events) {
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
		DataBaseHelper.DATABASE_TABLE = tableName;
		DataBaseHelper.DATABASE_CREATE = getCommandDatabaseCreate(names,events);
		DataBaseHelper.DATABASE_UPGRADE = "DROP TABLE IF EXISTS "+ DATABASE_TABLE;
	}

	private String getCommandDatabaseCreate(String[] names, String[] events) {
		String stringCreateCommand = "create table "+DATABASE_TABLE+"("+ KEY_ROW_ID +" integer primary key autoincrement,";
		String strColumn = "";
		for (int i = 0; i < events.length; i++) {
			if (i == events.length-1)
				strColumn = strColumn + events[i] +" text" + ");";
			else
				strColumn = strColumn + events[i] +" text" + ",";
		}
		stringCreateCommand = stringCreateCommand + strColumn;
		LOG.v("create table ", stringCreateCommand);
		return stringCreateCommand;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		LOG.i(TAG, "database created");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DATABASE_UPGRADE);
		onCreate(db);
		LOG.i(TAG, "database upgraded");
	}
}
