package com.example.slidingsimplesample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.book.db.DBMS;
import com.book.db.Detail_DBMS;







import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
	final int Camera_CALL = 1;
	final int Camera_HD = 2;
	final int Gal = 3;
	String Path; // 저장경로
	DBMS db;
	private Button button;
	SeekBar seekBar;
	private int num = 999;
	private static int current_page = 0;
	
	public static String title;
	Book_info book_info;
	static LinearLayout seekbar_layout;
	static LinearLayout grade_layout;
	static boolean finish_book=false; // 책을 다 읽었는지
	RatingBar rating;
	float grade_num=0;
	
	//String title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		getActionBar().setDisplayShowTitleEnabled(true);
        
        //타이틀이름보이게
        getActionBar().setDisplayHomeAsUpEnabled(false);
        //타이틀 홈키 보이게
       
		 db = new DBMS(getApplicationContext());
		
		ImageView imageview = (ImageView)findViewById(R.id.detail_img1);
		TextView title_tv = (TextView)findViewById(R.id.detail_title);
		TextView author_tv = (TextView)findViewById(R.id.detail_author);
		rating = (RatingBar)findViewById(R.id.detail_grade);
	
		
		Intent intent = getIntent();
		 title = intent.getExtras().getString("title");
		 book_info = db.dataSelect(title);
		//Toast.makeText(getApplicationContext(), book_info.getAuthor(), 1).show();
		
		
		GetBitmap bitmap = new GetBitmap();
		String fname = book_info.getImage_source();
		
		if(fname!=null){
			Bitmap bm = bitmap.getBitmap(fname);
			title_tv.setText(book_info.getTitle());
			author_tv.setText(book_info.getAuthor());
			imageview.setImageBitmap(bm);
		}
		
		
		
///////////////////////////////////////////////////////추가/////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
			
		//Log.i("time", book_info.getReg_end_date());

//button 총페이지수 입력 버튼~~~~~~~~~~~~~~~
				
			button = (Button) findViewById(R.id.page_input_button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				showInputDialog();
			}
		});
				final TextView reading = (TextView) findViewById(R.id.seek);

//seekBar~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				seekBar = (SeekBar) findViewById(R.id.seekbar);
				 
				 if(book_info.getTotal_page()!=0)
					 num = book_info.getTotal_page();
				 current_page = book_info.getCurrent_page();
				 
				  seekbar_layout = (LinearLayout)findViewById(R.id.seekbar_layout);		 
				  grade_layout = (LinearLayout)findViewById(R.id.grade_layout);
				 
				  //다 읽었으면~
				 if(num==current_page){
					 seekbar_layout.setVisibility(View.INVISIBLE);
					 grade_layout.setVisibility(View.VISIBLE);
				 }else{
					 seekbar_layout.setVisibility(View.VISIBLE);
					 grade_layout.setVisibility(View.INVISIBLE);
				 }			 
				 
				 seekBar.setMax(num);
				 
				 reading.setText("페이지" + current_page + "/" + num);
				 				 
				 
				seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						/*if(book_info.getReg_end_date()!=null){
							DBMS db = new DBMS(getApplicationContext());
							book_info = db.dataSelect(title);
						    seekbar_layout.setVisibility(View.INVISIBLE);
							grade_layout.setVisibility(View.VISIBLE);}*/
					}

					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
							boolean fromUser) {
						
						reading.setText("페이지" + progress + "/" + num);
						current_page = progress;
						if (progress == num) {
							
							AlertDialog dialog = createDialogBox();
						    dialog.show();
						    
						    /*if(finish_book){
						    	current_page += 1;
						    	DialogFragment newFragment = new DatePickerFragment();
						    	newFragment.show(getFragmentManager(), "datePicker");
						    }else{
						    	 // 다 읽으셨나요라는 다이얼로그에서 취소를 누르면 현재 페이지를 -1시켜서 seekbar가 없어지지 않도록 한다.
						    	current_page -= 1;
						    }*/
						}			
					}
					
				});
				
				
//별점~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~				
				rating.setStepSize((float) 0.5); //별 색깔이 1칸씩줄어들고 늘어남 0.5로하면 반칸씩 들어감
			   // rating.setRating(((float)2.5);   // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
			    rating.setIsIndicator(false);  //false - 사용자가 변경가능
			    
			    grade_num = book_info.getGrade();
			    rating.setRating(grade_num);
			    
		        rating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
		        	 
		            @Override
		            public void onRatingChanged(RatingBar ratingBar, float rating,
		                    boolean fromUser) {
		            	//tv01.setText(""+rating);
		            	grade_num=rating;
		            }
		        });
		         //rating2.setStepSize((float) 0.5);
		        	// rating2.setIsIndicator(true);//true - 별점만 표시 사용자가 변경 불가 
		        	// rating2.setRating(num);// 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
				//if
			
				//글쓰기 목록
				

				
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
	
		ArrayList<Book_info_detail> book_dt = new ArrayList<Book_info_detail>();
		Detail_DBMS dt_db = new Detail_DBMS(getApplicationContext());
		book_dt = dt_db.dataSelect(title); 
		//Toast.makeText(getApplicationContext(), book_dt.get(0).getCamera_pic(), 1).show();
		ListView lv = (ListView)findViewById(R.id.dt_listview);
		DetailAdapter da = new DetailAdapter(getApplicationContext(), R.layout.detail_list, book_dt);
		lv.setAdapter(da);
		
	
		
	}//onCreate가 끝나는 지점...
	//액션바
	 public boolean onCreateOptionsMenu(Menu menu) {
		   getMenuInflater().inflate(R.menu.detail, (android.view.Menu) menu);
	        return true;
	    }
	    public boolean onOptionsItemSelected(MenuItem item) {

	 
	        switch(item.getItemId()){//메뉴실행버튼
	        case android.R.id.home:
				String text = "메인화면으로 돌아갑니다.";
	            Toast.makeText(DetailActivity.this, text, Toast.LENGTH_SHORT).show();
	           
	          //백버튼 누르면 뒤로간다 ㅋ
	        case R.id.superdetail_forward:
	            Intent intent = new Intent(DetailActivity.this, SuperDetail.class);
	            intent.putExtra("d_title", title);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	 
	 			return true;

	             
	        default:
	            return true;
	        }
	   
	    }
	
//////////////////////////////////////////추가///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	//갑자기 꺼버리거나 다른페이지로 넘어갈시 데이터 저장
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		db.dataUpdate_i("total_page", num, title);
		db.dataUpdate_i("current_page", current_page, title);
		db.dataUpdate_f("grade", grade_num, title);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		db.dataUpdate_i("total_page", num, title);
		db.dataUpdate_i("current_page", current_page, title);
		db.dataUpdate_f("grade", grade_num, title);
	}
	
	
	
	
	
	//총페이지 입력 다이얼로그
	protected void showInputDialog() {

		LayoutInflater layoutInflater = LayoutInflater
				.from(DetailActivity.this);
		View promptView = layoutInflater.inflate(R.layout.page_dialog, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				DetailActivity.this);
		alertDialogBuilder.setView(promptView);

		final EditText editText = (EditText) promptView
				.findViewById(R.id.edittext);
		// 설정 window
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						num = Integer.parseInt(editText.getText().toString());
						
						seekBar.setMax(num);

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	
	//책을 다읽었는지 확인하는 다이얼로그
	private AlertDialog createDialogBox(){
	     AlertDialog.Builder builder = new AlertDialog.Builder(this);

	     builder.setMessage("다 읽으셨나요?");
	     builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
	    	 public void onClick(DialogInterface dialog, int whichButton){
	      
	    	  //seekbar_layout.setVisibility(View.INVISIBLE);
	    	  //grade_layout.setVisibility(View.VISIBLE);
	    	  	  
			    	DialogFragment newFragment = new DatePickerFragment();
			    	newFragment.show(getFragmentManager(), "datePicker");

	      	}
	     });
	     
	     
	     builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
	    	 public void onClick(DialogInterface dialog, int whichButton){

	    	  current_page -= 1;
	    	  dialog.dismiss();
	    	
	      	}
	     });
	 
	     AlertDialog dialog = builder.create();
	     return dialog; 
	  }
	
	
	//다 읽은 날짜 확인하는 다이얼로그
	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
			
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			//Log.i("date", day+"" );
			current_page -=1; //취소하면 현재페이지를 -1시킨 상태로 끝... seekbar가 안없어짐
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			DBMS db = new DBMS(view.getContext());
			db.dataUpdate_s("reg_end_date", year+"/"+month+"/"+day,title );
			
				seekbar_layout.setVisibility(View.INVISIBLE);
				grade_layout.setVisibility(View.VISIBLE);
		    	 // 다 읽으셨나요라는 다이얼로그에서 취소를 누르면 현재 페이지를 -1시켜서 seekbar가 없어지지 않도록 한다.
		    	current_page += 1; //위에서 -1해놓은것 복구
		    
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//superdetail 액티비티에서 넘어올때 listview를 새로 뿌려준다.
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if(requestCode == 1001 && resultCode == 1002){
				
				
				
				ImageView img = (ImageView)findViewById(R.id.dtl_img);
				
				String title = data.getStringExtra("sd_title");
				//Book_info book = db.dataSelect(title);
				ArrayList<Book_info_detail> book_dt = new ArrayList<Book_info_detail>();
				Detail_DBMS dt_db = new Detail_DBMS(getApplicationContext());
				book_dt = dt_db.dataSelect(title); 
				
				ListView lv = (ListView)findViewById(R.id.dt_listview);
				DetailAdapter da = new DetailAdapter(getApplicationContext(), R.layout.detail_list, book_dt);
				lv.setAdapter(da);
			}
		}
	
}
	



