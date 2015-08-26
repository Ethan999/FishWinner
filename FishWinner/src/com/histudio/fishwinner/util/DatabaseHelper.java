package com.histudio.fishwinner.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final int VERSION = 1;
	public String SQL = "create table "+Constants.TB_NAME +"(score int)";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public DatabaseHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}
	
	public DatabaseHelper(Context context, String name) {
		this(context, name, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase sql) {

		System.out.println("----------database oncreate--------------");	
		sql.execSQL(SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		System.out.println("Updata Vertion");

	}

}
