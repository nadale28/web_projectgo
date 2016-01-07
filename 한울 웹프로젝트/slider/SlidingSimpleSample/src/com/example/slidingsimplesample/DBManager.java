package com.example.slidingsimplesample;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper{

	public DBManager(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table item_list ( item_id integer, item_name varchar2 )");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	
    public void insert(String query) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "insert into item_list values(null, '"+query+"');";
        db.execSQL(str);
        db.close();     
    }
     
    public void update(int num, String query) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "update item_list  item_name = '"+query+"' where item_id = "+num+";";
        db.execSQL(str);
        db.close();     
    }
     
    public void delete(String query) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "delete from item_list where item_name = '"+query+"';";
        db.execSQL(str);
        db.close();     
    }
    
    public ArrayList<String> deleteAll(ArrayList<String> list) {
        SQLiteDatabase db = getWritableDatabase();
        list.clear();
        
        String str = "delete from item_list;";
        db.execSQL(str);
        db.close(); 
        
        return list;
    }
    
    public ArrayList<String> select(ArrayList<String> list, String query){
    	SQLiteDatabase db = getReadableDatabase();
    	String str = "";
    	
    	list.clear();
    	Cursor cursor = db.rawQuery("select * from item_list where item_name = '"+query+"';", null);
    	while(cursor.moveToNext()){
    		str += cursor.getInt(0)+" : "+cursor.getString(1);
    		list.add(str);
    	}
    	
    	return list;
    }
     
    public ArrayList<String> selectAll(ArrayList<String> list) {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
         
        list.clear();
        Cursor cursor = db.rawQuery("select * from item_list", null);
        while(cursor.moveToNext()) {
        	str = cursor.getInt(0)+" : "+cursor.getString(1);
            list.add(str);
        }
         
        return list;
    }
    public ArrayList<String> reg_date_count(ArrayList<String> list) {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
         
        list.clear();
        Cursor cursor = db.rawQuery("select * from item_list", null);
        while(cursor.moveToNext()) {
        	str = cursor.getInt(0)+" : "+cursor.getString(1);
            list.add(str);
        }
         
        return list;
    }
    


}
