package com.book.db;

import java.util.ArrayList;

import com.book.test1122.Book_info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class DBMS {
	Context context;
	//������ ���� ��ü
	SQLiteDatabase db;
	
	public DBMS(Context context){
		this.context = context;
		open();
	}
	public void open(){
		DBHelper dbhelper = new DBHelper(context);
		db = dbhelper.getWritableDatabase();
	}
	public void dataInput(String title,String author, String image){
		String sql="";
		sql = "		insert into book"
				+ " values(null, '"+title+"', '"+author+"', '"+image+"');";
		db.execSQL(sql);
		//db.execSQL("insert into contact values('"+data+"')");
		
	}
	
	//dateInput_s, dataInput_i... Ư�� ������ �߰�
	//å��, ��������, ���� ���� ���߿� ���� �߰��ϹǷ� �������.
	public void dataInput_s(String col, String val, String title){
		String sql="";
		sql = "		insert into book("+col+")"
				+ " values(null, '"+val+"')"
				+ " where title = '"+title+"';";
		db.execSQL(sql);			
	}
	
	public void dataInput_i(String col, int val, String title){
		String sql="";
		sql = "		insert into book("+col+")"
				+ " values(null, "+val+")"
				+ " where title = '"+title+"';";
		db.execSQL(sql);
	}
	
	//dataUpdate_s, dataUpdate_i... Ư�� ������ ����
	//å��, å�̸�, �������� �� �����Ҷ�...
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
				+ " where title = '"+title+"';";
		db.execSQL(sql);
	}
	
	public void dataAllDelete(){
		db.execSQL("delete from book");
	}
	
	public void dataDelete(String title){
		String sql = "";
		sql = "		delete from book"
				+ "	where title = '"+title+"'; ";
		db.execSQL(sql);
	}
	
	
	public ArrayList<Book_info> dataAllSelect(){
		ArrayList<Book_info> list = new ArrayList<Book_info>();
		
		String sql="";
		sql = "		select * from book"
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
		}
		return book;
	}
	
	

}
