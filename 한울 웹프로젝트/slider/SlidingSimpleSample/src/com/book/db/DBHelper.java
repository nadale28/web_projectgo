/*package com.book.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	static String name = "MyDataBase.db";
	
	public DBHelper(Context context) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//onCreate는 MyDataBase.db 파일이 없을때 호출된다.
		String sql ="";
		sql = "		create table book("
				+ " id integer primary key,"
				+ " title varchar2,"
				+ " author varchar2,"
				+ " image varchar2,"
				+ " bookcase varchar2"
				+ " grade integer,"
				+ " reg_date varchar2,"
				+ " record date,"
				+ " page integer,"
				+ " content varchar2,"
				+ " record_page integer,"
				+ " camera_pic varchar2,"
				+ " picture varchar2"
				+ " );";
		db.execSQL(sql);
		//db.execSQL("Create Table contact(code TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop Table contact()");
		
	}
}
*/
package com.book.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	static String name = "MyDataBase.db";
	
	public DBHelper(Context context) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//onCreate는 MyDataBase.db 파일이 없을때 호출된다.
		String sql ="";
		/*sql = "		create table book("
				+ " id integer primary key,"
				+ " title varchar2,"
				+ " author varchar2,"
				+ " image varchar2,"
				+ " bookcase varchar2,"
				+ " grade integer,"
				+ " reg_start_date varchar2,"
				+ " reg_end_date varchar2,"
				+ " total_page integer"
				+ " );";*/
		/*sql = "		create table book("
				+ " id integer primary key,"
				+ " title varchar2,"
				+ " author varchar2,"
				+ " image varchar2,"
				+ " bookcase varchar2,"
				+ " grade integer,"
				+ " reg_start_date varchar2,"
				+ " reg_end_date varchar2,"
				+ " total_pagetotal_page integer,"
				+ " current_page integer"
				+ " );";*/
		sql = "		create table book("
				+ " id integer primary key,"
				+ " title varchar2,"
				+ " author varchar2,"
				+ " image varchar2,"
				+ " bookcase varchar2,"
				+ " grade float,"
				+ " reg_start_date varchar2,"
				+ " reg_end_date varchar2,"
				+ " total_page integer,"
				+ " current_page integer"
				+ " );";
		db.execSQL(sql);
		//db.execSQL("Create Table contact(code TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop Table contact()");
		
	}
}
