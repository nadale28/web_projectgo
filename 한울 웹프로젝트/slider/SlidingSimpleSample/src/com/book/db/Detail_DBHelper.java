package com.book.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Detail_DBHelper extends SQLiteOpenHelper {

static String name = "MyDataBase1.db";
	
	public Detail_DBHelper(Context context) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//onCreate는 MyDataBase.db 파일이 없을때 호출된다.
		String sql ="";
		sql = "		create table book_detail("
				+ " title varchar2, "
				+ " camera_pic varchar2, "
				+ " record_time varchar2, "
				+ " record_page integer, "
				+ " content varchar2,"
				+ " voice_file varchar2"
				
				+ " );";
		db.execSQL(sql);
		//db.execSQL("Create Table contact(code TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop Table contact()");
		
	}
}

