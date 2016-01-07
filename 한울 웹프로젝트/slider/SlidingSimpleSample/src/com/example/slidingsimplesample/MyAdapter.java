package com.example.slidingsimplesample;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.example.slidingsimplesample.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private ArrayList<Book_info> book_info;
	private int layout;
	private Context context;
	public MyAdapter(Context context, int layout, ArrayList<Book_info> book_info) {
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.layout = layout;
		this.book_info = book_info;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return book_info.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return book_info.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView = inflater.inflate(layout, parent, false);
		}
		ImageView imageview = (ImageView)convertView.findViewById(R.id.list_imageview);
		TextView title_view = (TextView)convertView.findViewById(R.id.list_title_textview);
		TextView author_view = (TextView)convertView.findViewById(R.id.list_author_textview);
			
						
		title_view.setText(book_info.get(position).getTitle());
		author_view.setText(book_info.get(position).getAuthor());
		
		ImageLoaderTask imgtask = 
				new ImageLoaderTask(imageview, book_info.get(position).getImage_source());
		imgtask.execute();
		//imageview.setImageBitmap();;
		
		//return convertView;
		return convertView;
	}

}
