package com.example.app_mainproject;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

public class DataStorage {

	private static SQLiteDatabase db;
	private static SQLiteHelper helper;

	private static String[] allColumnsContact = { SQLiteHelper.COLUMN_ID,
			SQLiteHelper.COLUMN_CONTACT_NAME, SQLiteHelper.COLUMN_SKYPE_ID,
			SQLiteHelper.COLUMN_POSITION, SQLiteHelper.COLUMN_IMAGE };
	private static String[] allColumnsKeyValueStore = {
			SQLiteHelper.COLUMN_KEY, SQLiteHelper.COLUMN_VALUE };

	public static void init(Context context) {
		helper = new SQLiteHelper(context);
	}

	public static void Close() {
		if (helper != null)
			helper.close();
	}

	private static SQLiteDatabase DB() {
		if (db == null) {
			db = helper.getWritableDatabase();
		}
		return db;
	}

	public static int CreateContact(String contactName, String skypeID,
			Bitmap contactPictureBitmap, int position) {
		ContentValues values = new ContentValues();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		contactPictureBitmap.compress(CompressFormat.PNG, 1, baos);
		values.put(SQLiteHelper.COLUMN_CONTACT_NAME, contactName);
		values.put(SQLiteHelper.COLUMN_SKYPE_ID, skypeID);
		values.put(SQLiteHelper.COLUMN_IMAGE, baos.toByteArray());
		values.put(SQLiteHelper.COLUMN_POSITION, position);
		return (int) DB().insert(SQLiteHelper.TABLE_CONTACTS, null, values);
	}

	private static Cursor queryContact(int uid) {
		return DB().query(SQLiteHelper.TABLE_CONTACTS, allColumnsContact,
				SQLiteHelper.COLUMN_ID + " = " + uid, null, null, null, null);
	}

	private static Contact cursorToContact(Cursor c) {
		Contact ret = new Contact();
		int uid = c.getInt(0);
		String contactName = c.getString(1);
		String skypeID = c.getString(2);
		int position = c.getInt(3);
		byte[] bitmapArr = c.getBlob(4);
		ret.uid = uid;
		ret.setName(contactName);
		ret.setContactId(skypeID);
		ret.setPosition(position);
		ret.setImage(BitmapFactory.decodeByteArray(bitmapArr, 0,
				bitmapArr.length));
		return ret;
	}

	public static Contact GetContact(int uid) {
		Cursor c = queryContact(uid);
		if (c.getColumnCount() == 0)
			return null;
		c.moveToFirst();
		return cursorToContact(c);
	}

	public static List<Contact> GetAllContacts() {
		List<Contact> ret = new ArrayList<>();
		Cursor c = DB().query(SQLiteHelper.TABLE_CONTACTS, allColumnsContact,
				null, null, null, null, null);
		if (c.moveToFirst())
			do {
				ret.add(cursorToContact(c));
			} while (c.moveToNext());
		return ret;
	}

	public static boolean RemoveContact(int uid) {
		int rem = DB().delete(SQLiteHelper.TABLE_CONTACTS,
				SQLiteHelper.COLUMN_ID + " = " + uid, null);
		return rem != 0;
	}

	public static boolean UpdateContact(int uid, String contactName,
			String skypeID, Bitmap contactPictureBitmap, int position) {
		ContentValues values = new ContentValues();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		contactPictureBitmap.compress(CompressFormat.PNG, 1, baos);
		values.put(SQLiteHelper.COLUMN_CONTACT_NAME, contactName);
		values.put(SQLiteHelper.COLUMN_SKYPE_ID, skypeID);
		values.put(SQLiteHelper.COLUMN_IMAGE, baos.toByteArray());
		values.put(SQLiteHelper.COLUMN_POSITION, position);
		return DB().update(SQLiteHelper.TABLE_CONTACTS, values,
				SQLiteHelper.COLUMN_ID + " = " + uid, null) != 0;
	}

	private static Cursor queryKeyValueStore(String key) {
		return DB().query(SQLiteHelper.TABLE_KEYVALUESTORE,
				allColumnsKeyValueStore,
				SQLiteHelper.COLUMN_KEY + " LIKE '" + key + "'", null, null, null,
				null);
	}

	public static Integer GetInt(String key) {
		Cursor c = queryKeyValueStore(key);
		if (c.getCount() == 0)
			return null;
		c.moveToFirst();
		return ByteBuffer.wrap(c.getBlob(1)).getInt();
	}

	public static Boolean GetBoolean(String key) {
		Cursor c = queryKeyValueStore(key);
		if (c.getCount() == 0)
			return null;
		c.moveToFirst();
		return ByteBuffer.wrap(c.getBlob(1)).get() != 0;
	}

	public static String GetString(String key) {
		Cursor c = queryKeyValueStore(key);
		if (c.getCount() == 0)
			return null;
		c.moveToFirst();
		return new String(c.getBlob(1));
	}

	public static boolean PutInt(String key, Integer i) {
		if (key == null)
			return false;
		if (i == null)
			return DeleteKey(key);
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_KEY, key);
		values.put(SQLiteHelper.COLUMN_VALUE, ByteBuffer.allocate(4).putInt(i).array());
		int rem = DB().update(SQLiteHelper.TABLE_KEYVALUESTORE, values,
				SQLiteHelper.COLUMN_KEY + " LIKE '" + key + "'", null);
		if (rem == 0) {
			DB().insert(SQLiteHelper.TABLE_KEYVALUESTORE, null, values);
		}
		return rem != 0;
	}

	private static boolean DeleteKey(String key) {
		int rem = DB().delete(SQLiteHelper.TABLE_KEYVALUESTORE,
				SQLiteHelper.COLUMN_KEY + " LIKE '" + key + "'", null);
		return rem != 0;
	}

	public static boolean PutBoolean(String key, Boolean b) {
		if (key == null)
			return false;
		if (b == null)
			return DeleteKey(key);
		byte bool = (byte) (b ? 1 : 0);
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_KEY, key);
		values.put(SQLiteHelper.COLUMN_VALUE, ByteBuffer.allocate(1).put(bool).array());
		int rem = DB().update(SQLiteHelper.TABLE_KEYVALUESTORE, values,
				SQLiteHelper.COLUMN_KEY + " LIKE '" + key + "'", null);
		if (rem == 0) {
			DB().insert(SQLiteHelper.TABLE_KEYVALUESTORE, null, values);
		}
		return rem != 0;
	}

	public static boolean PutString(String key, String s) {
		if (key == null)
			return false;
		if (s == null)
			return DeleteKey(key);
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_KEY, key);
		values.put(SQLiteHelper.COLUMN_VALUE, s.getBytes());
		int rem = DB().update(SQLiteHelper.TABLE_KEYVALUESTORE, values,
				SQLiteHelper.COLUMN_KEY + " LIKE '" + key + "'", null);
		if (rem == 0) {
			DB().insert(SQLiteHelper.TABLE_KEYVALUESTORE, null, values);
		}
		return rem != 0;
	}

	public static void Clear() {
		helper.onUpgrade(DB(), 1, 1);
	}
}