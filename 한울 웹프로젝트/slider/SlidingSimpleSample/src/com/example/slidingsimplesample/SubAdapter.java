package com.example.slidingsimplesample;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;







import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SubAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<Book_info> book_info;
	private int layout;
	private Context context;
	
	
	public SubAdapter(Context context, int layout, ArrayList<Book_info> book_info) {
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.layout = layout;
		this.book_info = book_info;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 7;
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
		ViewHolder holder; 
		
		if(convertView==null){
			convertView = inflater.inflate(layout, parent, false);
			holder = new ViewHolder();
			holder.text_title = (TextView)convertView.findViewById(R.id.list_title_textview);
			holder.text_author = (TextView)convertView.findViewById(R.id.list_author_textview);
			holder.imageview = (ImageView)convertView.findViewById(R.id.list_imageview);
			convertView.setTag(holder);		
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.text_title.setText(book_info.get(position).getTitle());
		holder.text_author.setText(book_info.get(position).getAuthor());

		holder.position = position;
		ImageLoaderTask imgtask = 
				new ImageLoaderTask(holder,position,holder.imageview, book_info.get(position).getImage_source());
		
		imgtask.execute();
		return convertView;
	}
	
	
	private static class ViewHolder {
	    public TextView text_title;
	    public TextView text_author;
		public ImageView imageview;
	    public int position;   
	}
	
	
	public class ImageLoaderTask extends AsyncTask<Void, Void, Bitmap>{
		 /** The target image view to load an image */
	    //private ImageView imageView;
	    private int position;
	    private ViewHolder holder;
	    /** The address where an image is stored. */
	    private String imageAddress;
	    private ImageView imageView;
	    public ImageLoaderTask(ViewHolder holder, int position, ImageView imageview, String imageAddress) {
	        //this.imageView = imageView;
	        this.imageAddress = imageAddress;
	        this.imageView = imageview;
	        this.holder = holder;
	        this.position = position;
	    }

	    @Override
	    protected Bitmap doInBackground(Void... params) {
	        Bitmap bitmap = null;
	        try {
	            InputStream is = new java.net.URL(this.imageAddress).openStream();
	            bitmap = BitmapFactory.decodeStream(is);
	        } catch (IOException e) {
	            Log.e("ImageLoaderTask", "Cannot load image from " + this.imageAddress);
	        }
	        return bitmap;
	    }

	    @Override
	    protected void onPostExecute(Bitmap bitmap) {
	    	if(holder.position == this.position){
	    		Log.e("ImageLoaderTask", "Cannot load image from " + this.holder.imageview);
	    		this.imageView.setImageBitmap(bitmap);}
	    }
	}
	
	
	
}
