package com.book.db;

import java.util.ArrayList;




import com.example.slidingsimplesample.Book_info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class DBMS {
	Context context;
	//쿼리를 날릴 객체
	SQLiteDatabase db;
	
	public DBMS(Context context){
		this.context = context;
		open();
	}
	public void open(){
		DBHelper dbhelper = new DBHelper(context);
		db = dbhelper.getWritableDatabase();
	}
	public void dataInput(String title,String author, String bookcase,String image ){
		String sql="";
		sql = "		insert into book(title, author, bookcase,image)"
				+ " values('"+title+"', '"+author+"', '"+bookcase+"', '"+image+"');";
		db.execSQL(sql);
		//db.execSQL("insert into contact values('"+data+"')");
		
	}
	
	//dateInput_s, dataInput_i... 특정 데이터 추가 만들긴했으나 쓸데가없다...
	public void dataInput_s(String col, String val, String title){
		String sql="";
		sql = "		insert into book("+col+")"
				+ " values( '"+val+"')"
				+ " ;";
		db.execSQL(sql);			
	}
	
	public void dataInput_i(String col, int val, String title){
		String sql="";
		sql = "		insert into book("+col+")"
				+ " values( "+val+")";
		
		db.execSQL(sql);
	}
	
	//dataUpdate_s, dataUpdate_i... 특정 데이터 변경
	//책장, 책이름, 페이지수 등 변경할때...
	public void dataUpdate_s(String col, String val, String title){
		String sql="";
		sql = "		update book"
				+ " set "+col+"='"+val+"'"
				+ " where title = '"+title+"';";
		db.execSQL(sql);			
	}
	
	public void dataUpdate_i(String col, int val, String title){
		String sql="";
		sql = "		update book"
				+ " set "+col+"="+val
				+ " where title = '"+title+"';"
						+ "";
		db.execSQL(sql);
	}
	public void dataUpdate_f(String col, float val, String title){
		String sql="";
		sql = "		update book"
				+ " set "+col+" = "+val
				+ " where title = title";
		db.execSQL(sql);
	}
	
	public void dataDelete(String title){
		String sql="";
		sql = "		delete from book"
				+ "	where title = '"+title+"'; ";
		db.execSQL(sql);
		}
	
	
	
	/*create table book("
			+ " id integer primary key,"
			+ " title varchar2,"
			+ " author varchar2,"
			+ " image varchar2,"
			+ " bookcase varchar2,"
			+ " grade integer,"
			+ " reg_start_date date,"
			+ " reg_end_date date"
			+ " total_page integer,"
			+ " );";*/
	
	public ArrayList<Book_info> dataAllSelect(){
		ArrayList<Book_info> list = new ArrayList<Book_info>();
		String sql="";
		sql = "		select * from book"
				+ " order by id;";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			Book_info book = new Book_info();
			
			book.setTitle(cursor.getString(1));
			//Log.i("dd", cursor.getString(1));
			book.setAuthor(cursor.getString(2));
			book.setImage_source(cursor.getString(3));
			book.setBookcase(cursor.getString(4));
			book.setGrade(cursor.getFloat(5));
			book.setReg_start_date(cursor.getString(6));
			book.setReg_end_date(cursor.getString(7));
			book.setTotal_page(cursor.getInt(8));
			book.setCurrent_page(cursor.getInt(9));
			Log.i("page", cursor.getInt(9)+"");
			Log.i("hello", cursor.getInt(2)+"");
			list.add(book);		
		}											
		return list;		
	}
	
	
	public Book_info dataSelect(String title){
		Book_info book = new Book_info();
		String sql="";
		sql = "		select * from book"
				+ " where title = '"+title+"';";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			book.setTitle(cursor.getString(1));
			book.setAuthor(cursor.getString(2));
			book.setImage_source(cursor.getString(3));
			book.setBookcase(cursor.getString(4));
			book.setGrade(cursor.getFloat(5));
			book.setReg_start_date(cursor.getString(6));
			book.setReg_end_date(cursor.getString(7));
			book.setTotal_page(cursor.getInt(8));
			book.setCurrent_page(cursor.getInt(9));
			
		}
		return book;
		
	}
	public ArrayList<Book_info> dataselect_booklist(String bookcase){
		ArrayList<Book_info> list = new ArrayList<Book_info>();
		
		String sql="";
		sql = "		select * from book"
				+ " where bookcase = '"+bookcase+"'"
				+ " order by id;";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			Book_info book = new Book_info();
			book.setTitle(cursor.getString(1));
			book.setAuthor(cursor.getString(2));
			book.setImage_source(cursor.getString(3));
			list.add(book);		
		}
		return list;
	}
	/*public String datacount(String reg_end_date){
		String i="";
		
		String sql="";
		sql = "		select count(*) from book"
				+ " where bookcase like  '%"+reg_end_date+"%'"
				+ " ;";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			
			i=cursor.getString(0);
					
		}
		return i;
	}*/
	public String datacount(String reg_end_date){
		String i="";
		
		String sql="";
		sql = "		select count(*) from book"
				+ " where reg_end_date like  '"+reg_end_date+"%'"
				+ " ;";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			
			i=cursor.getString(0);
					
		}
		return i;
	}
	

}

	
	


	
