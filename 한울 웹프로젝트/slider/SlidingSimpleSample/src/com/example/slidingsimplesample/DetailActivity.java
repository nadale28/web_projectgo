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
	String Path; // ������
	DBMS db;
	private Button button;
	SeekBar seekBar;
	private int num = 999;
	private static int current_page = 0;
	
	public static String title;
	Book_info book_info;
	static LinearLayout seekbar_layout;
	static LinearLayout grade_layout;
	static boolean finish_book=false; // å�� �� �о�����
	RatingBar rating;
	float grade_num=0;
	
	//String title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		getActionBar().setDisplayShowTitleEnabled(true);
        
        //Ÿ��Ʋ�̸����̰�
        getActionBar().setDisplayHomeAsUpEnabled(false);
        //Ÿ��Ʋ ȨŰ ���̰�
       
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
		
		
		
///////////////////////////////////////////////////////�߰�/////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
			
		//Log.i("time", book_info.getReg_end_date());

//button ���������� �Է� ��ư~~~~~~~~~~~~~~~
				
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
				 
				  //�� �о�����~
				 if(num==current_page){
					 seekbar_layout.setVisibility(View.INVISIBLE);
					 grade_layout.setVisibility(View.VISIBLE);
				 }else{
					 seekbar_layout.setVisibility(View.VISIBLE);
					 grade_layout.setVisibility(View.INVISIBLE);
				 }			 
				 
				 seekBar.setMax(num);
				 
				 reading.setText("������" + current_page + "/" + num);
				 				 
				 
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
						
						reading.setText("������" + progress + "/" + num);
						current_page = progress;
						if (progress == num) {
							
							AlertDialog dialog = createDialogBox();
						    dialog.show();
						    
						    /*if(finish_book){
						    	current_page += 1;
						    	DialogFragment newFragment = new DatePickerFragment();
						    	newFragment.show(getFragmentManager(), "datePicker");
						    }else{
						    	 // �� �����̳����� ���̾�α׿��� ��Ҹ� ������ ���� �������� -1���Ѽ� seekbar�� �������� �ʵ��� �Ѵ�.
						    	current_page -= 1;
						    }*/
						}			
					}
					
				});
				
				
//����~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~				
				rating.setStepSize((float) 0.5); //�� ������ 1ĭ���پ��� �þ 0.5���ϸ� ��ĭ�� ��
			   // rating.setRating(((float)2.5);   // ó�������ٶ�(������ �Ѱ�������) default ���� 0  �̴�
			    rating.setIsIndicator(false);  //false - ����ڰ� ���氡��
			    
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
		        	// rating2.setIsIndicator(true);//true - ������ ǥ�� ����ڰ� ���� �Ұ� 
		        	// rating2.setRating(num);// ó�������ٶ�(������ �Ѱ�������) default ���� 0  �̴�
				//if
			
				//�۾��� ���
				

				
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
	
		ArrayList<Book_info_detail> book_dt = new ArrayList<Book_info_detail>();
		Detail_DBMS dt_db = new Detail_DBMS(getApplicationContext());
		book_dt = dt_db.dataSelect(title); 
		//Toast.makeText(getApplicationContext(), book_dt.get(0).getCamera_pic(), 1).show();
		ListView lv = (ListView)findViewById(R.id.dt_listview);
		DetailAdapter da = new DetailAdapter(getApplicationContext(), R.layout.detail_list, book_dt);
		lv.setAdapter(da);
		
	
		
	}//onCreate�� ������ ����...
	//�׼ǹ�
	 public boolean onCreateOptionsMenu(Menu menu) {
		   getMenuInflater().inflate(R.menu.detail, (android.view.Menu) menu);
	        return true;
	    }
	    public boolean onOptionsItemSelected(MenuItem item) {

	 
	        switch(item.getItemId()){//�޴������ư
	        case android.R.id.home:
				String text = "����ȭ������ ���ư��ϴ�.";
	            Toast.makeText(DetailActivity.this, text, Toast.LENGTH_SHORT).show();
	           
	          //���ư ������ �ڷΰ��� ��
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
	
//////////////////////////////////////////�߰�///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	//���ڱ� �������ų� �ٸ��������� �Ѿ�� ������ ����
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
	
	
	
	
	
	//�������� �Է� ���̾�α�
	protected void showInputDialog() {

		LayoutInflater layoutInflater = LayoutInflater
				.from(DetailActivity.this);
		View promptView = layoutInflater.inflate(R.layout.page_dialog, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				DetailActivity.this);
		alertDialogBuilder.setView(promptView);

		final EditText editText = (EditText) promptView
				.findViewById(R.id.edittext);
		// ���� window
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

	
	//å�� ���о����� Ȯ���ϴ� ���̾�α�
	private AlertDialog createDialogBox(){
	     AlertDialog.Builder builder = new AlertDialog.Builder(this);

	     builder.setMessage("�� �����̳���?");
	     builder.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
	    	 public void onClick(DialogInterface dialog, int whichButton){
	      
	    	  //seekbar_layout.setVisibility(View.INVISIBLE);
	    	  //grade_layout.setVisibility(View.VISIBLE);
	    	  	  
			    	DialogFragment newFragment = new DatePickerFragment();
			    	newFragment.show(getFragmentManager(), "datePicker");

	      	}
	     });
	     
	     
	     builder.setNegativeButton("���", new DialogInterface.OnClickListener(){
	    	 public void onClick(DialogInterface dialog, int whichButton){

	    	  current_page -= 1;
	    	  dialog.dismiss();
	    	
	      	}
	     });
	 
	     AlertDialog dialog = builder.create();
	     return dialog; 
	  }
	
	
	//�� ���� ��¥ Ȯ���ϴ� ���̾�α�
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
			current_page -=1; //����ϸ� ������������ -1��Ų ���·� ��... seekbar�� �Ⱦ�����
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			DBMS db = new DBMS(view.getContext());
			db.dataUpdate_s("reg_end_date", year+"/"+month+"/"+day,title );
			
				seekbar_layout.setVisibility(View.INVISIBLE);
				grade_layout.setVisibility(View.VISIBLE);
		    	 // �� �����̳����� ���̾�α׿��� ��Ҹ� ������ ���� �������� -1���Ѽ� seekbar�� �������� �ʵ��� �Ѵ�.
		    	current_page += 1; //������ -1�س����� ����
		    
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//superdetail ��Ƽ��Ƽ���� �Ѿ�ö� listview�� ���� �ѷ��ش�.
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
	



