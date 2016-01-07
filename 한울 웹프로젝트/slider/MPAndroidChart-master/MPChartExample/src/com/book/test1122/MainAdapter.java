package com.book.test1122;

import java.io.File;
import java.util.ArrayList;

import com.example.test1122.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private Context context;
	private int layout;
	private ArrayList<Book_info> book_list;
	GetBitmap bitmap = new GetBitmap();
	
    public MainAdapter(Context context, ArrayList<Book_info> book_list, int layout) {
        this.context = context;
        this.book_list = book_list;
        this.layout = layout;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return book_list.size();
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }
    private static class ViewHolder {
	    public TextView text_title;
		public ImageView imageview;
	    public int position;   
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView==null){
			convertView = inflater.inflate(layout, parent, false);
			holder = new ViewHolder();
			holder.text_title = (TextView)convertView.findViewById(R.id.grid_title);
			holder.imageview = (ImageView)convertView.findViewById(R.id.grid_img);
			
			convertView.setTag(holder);		
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.position = position;
		
				Book_info book = book_list.get(position);
		    	String fname = null;
		    	String title = null;
		    	fname = book.getImage_source();
		    	title = book.getTitle();
		    	
		    	if(fname!=null){
		    		
			        Bitmap resized = bitmap.getBitmap(fname);
			        
			        holder.text_title.setText(title);
			        holder.imageview.setImageBitmap(resized);
		    	}	
		return convertView;
	}
}
