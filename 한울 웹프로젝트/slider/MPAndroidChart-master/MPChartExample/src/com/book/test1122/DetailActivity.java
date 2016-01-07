package com.book.test1122;

import com.book.db.DBMS;
import com.example.test1122.R;
import com.example.test1122.R.id;
import com.example.test1122.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		DBMS db = new DBMS(getApplicationContext());
		Button back_btn = (Button)findViewById(R.id.detail_back_btn);
		ImageView imageview = (ImageView)findViewById(R.id.detail_img);
		TextView title_tv = (TextView)findViewById(R.id.detail_title);
		TextView author_tv = (TextView)findViewById(R.id.detail_author);
		
		
		Intent intent = getIntent();
		String title = intent.getExtras().getString("title");
		Book_info book_info = db.dataSelect(title);
		//Toast.makeText(getApplicationContext(), book_info.getAuthor(), 1).show();
		
		
		GetBitmap bitmap = new GetBitmap();
		String fname = book_info.getImage_source();
		
		if(fname!=null){
			Bitmap bm = bitmap.getBitmap(fname);
			title_tv.setText(book_info.getTitle());
			author_tv.setText(book_info.getAuthor());
			imageview.setImageBitmap(bm);
			
		}
		
	
		
		
		back_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DetailActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}

}
