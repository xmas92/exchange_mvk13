package com.example.app_mainproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_CONTACTS = "contacts";
	public static final String COLUMN_ID = "uid";
	public static final String COLUMN_CONTACT_NAME = "contactName";
	public static final String COLUMN_SKYPE_ID = "skypeID";
	public static final String COLUMN_POSITION = "position";
	public static final String COLUMN_IMAGE = "image";

	public static final String TABLE_KEYVALUESTORE = "keyvaluestore";
	public static final String COLUMN_KEY = "key";
	public static final String COLUMN_VALUE = "value";

	private static final String DATABASE_NAME = "storage.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE_CONTACTS = "create table "
			+ TABLE_CONTACTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_CONTACT_NAME
			+ " text not null," + COLUMN_SKYPE_ID + " text not null,"
			+ COLUMN_POSITION + " integer not null," + COLUMN_IMAGE
			+ " blob not null" + ");";
	private static final String DATABASE_CREATE_KEYVALUESTORE = "create table "
			+ TABLE_KEYVALUESTORE + "(" + COLUMN_KEY + " text primary key not null, "
			+ COLUMN_VALUE + " blob not null" + ");";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_CONTACTS);
		database.execSQL(DATABASE_CREATE_KEYVALUESTORE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEYVALUESTORE);
		onCreate(db);
	}

}