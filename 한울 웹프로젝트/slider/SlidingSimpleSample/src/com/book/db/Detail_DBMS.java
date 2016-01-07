package com.book.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.slidingsimplesample.Book_info_detail;



public class Detail_DBMS {
	Context context;
	//쿼리를 날릴 객체
	SQLiteDatabase db;
	
	public Detail_DBMS(Context context){
		this.context = context;
		open();
	}
	public void open(){
		Detail_DBHelper dbhelper = new Detail_DBHelper(context);
		db = dbhelper.getWritableDatabase();
	}
	/*,String voice*/
	public void dataInput(String title, String camera_pic, String record_time, String record_page, String content,String voice_file){
		String sql="";
		sql = "		insert into book_detail"
				+ " values('"+title+"',"+ "'"+camera_pic+"', '"+record_time+"', '"+record_page+"', "
			    + "'"+content+"',"+"'"+voice_file+"'"
				+ ");";
		db.execSQL(sql);
	}
	//							컬럼 		   값				제목
	//제목은 무조건 넣어야한다... 어떤 책에 어떤 기록을 남겼는지 알아야하니까
	public void dataInput_s(String col, String val, String title){
		String sql="";
		sql = "		insert into book_detail("+col+","+title+")"
				+ " values( '"+val+"','"+title+"')"
				+ " ;";
		db.execSQL(sql);			
	}
	
	//페이지를 넣을때는 int형으로 들어가서
	public void dataInput_i(String col, int val, String title){
		String sql="";
		sql = "		insert into book_detail("+col+","+title+")"
				+ " values( '"+val+"','"+title+"')"
				+ " ;";
		db.execSQL(sql);
	}
	
	//dataUpdate_s, dataUpdate_i... 데이터 수정
	public void dataUpdate_s(String col, String val, String title){
		String sql="";
		sql = "		update book_detail"
				+ " set "+col+"= '"+val+"'"
				+ " where title = '"+title+"' ;";
		db.execSQL(sql);			
	}
	
	public void dataUpdate_i(String col, int val, String title){
		String sql="";
		sql = "		update book_detail"
				+ " set "+col+"="+val
				+ " where title = '"+title+"' ;";
		db.execSQL(sql);
	}
	
	public void dataDelete(){
		db.execSQL("delete from book_detail");
	}
	
	
	/*create table book_detail("
			+ " title varchar2, "
			+ " camera_pic varchar2, "
			+ " record_time date, "
			+ " record_page integer, "
			+ " content varchar2"*/
	
	public ArrayList<Book_info_detail> dataAllSelect(){
		ArrayList<Book_info_detail> list = new ArrayList<Book_info_detail>();
		String sql="";
		sql = "		select * from book_detail"
				+ " order by record_time;";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			Book_info_detail book = new Book_info_detail();
			book.setTitle(cursor.getString(0));
			book.setCamera_pic(cursor.getString(1));
			book.setRecord_time(cursor.getString(2));
			book.setRecord_page(cursor.getInt(3));
			book.setRecord_content(cursor.getString(4));
			book.setVoice_file(cursor.getString(5));
			
			list.add(book);		
		}											
		return list;		
	}
	
	
	public ArrayList<Book_info_detail> dataSelect(String title){
		ArrayList<Book_info_detail> list = new ArrayList<Book_info_detail>();
		String sql="";
		sql = "		select * from book_detail"
				+ " where title = '"+title+"';";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Book_info_detail book = new Book_info_detail();
			book.setTitle(cursor.getString(0));
			book.setCamera_pic(cursor.getString(1));
			book.setRecord_time(cursor.getString(2));
			book.setRecord_page(cursor.getInt(3));
			book.setRecord_content(cursor.getString(4));
			book.setVoice_file(cursor.getString(5));
			list.add(book);
		}
		return list;
	}
	
	

}
