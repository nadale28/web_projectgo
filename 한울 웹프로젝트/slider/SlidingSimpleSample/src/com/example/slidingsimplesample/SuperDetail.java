/*

package com.example.slidingsimplesample;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.book.db.DBMS;





import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SuperDetail extends Activity implements View.OnClickListener, OnCompletionListener
{
	
	// 미리 상수 선언	
	  private static final int REC_STOP = 0;
	  private static final int RECORDING = 1;
	  private static final int PLAY_STOP = 0;
	  private static final int PLAYING = 1;
	  private static final int PLAY_PAUSE = 2;
	  
	  
	  private MediaPlayer mPlayer = null;
	  private int mRecState = REC_STOP;
	  private int mPlayerState = PLAY_STOP;
	  private SeekBar mRecProgressBar, mPlayProgressBar;
	  private Button mBtnStartRec, mBtnStartPlay, mBtnStopPlay;
	  private String  fullFilePath= null;
	  private TextView mTvPlayMaxPoint;
	  
	  private int mCurRecTimeMs = 0;
	  private int mCurProgressTimeDisplay = 0;
	  
	  
	  
	  
	  // 재생시 SeekBar 처리
	  Handler mProgressHandler2 = new Handler()
	  {
	    public void handleMessage(Message msg)
	    {
	      if (mPlayer == null) return;
	      
	      try
	      {
	        if (mPlayer.isPlaying())
	        {
	          mPlayProgressBar.setProgress(mPlayer.getCurrentPosition());
	          mProgressHandler2.sendEmptyMessageDelayed(0, 100);
	        }
	      }
	      catch (IllegalStateException e)
	      {}
	      catch (Exception e)
	      {}
	    }
	  };
	  
	final int Camera_CALL = 1;
	final int Camera_HD = 2;
	final int Gal = 3;
	final int Rec = 4;
	String Path; // 저장경로
    Uri uri=null;
    Bitmap bm=null;
    ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_super_detail);
		Path = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/attageImage.jpg";
		fullFilePath = "/sdcard/MyApp/Mylittle20151204112054Rec.mp4";
		
	    mBtnStartPlay = (Button) findViewById(R.id.btnStartPlay1);
	    mBtnStopPlay = (Button) findViewById(R.id.btnStopPlay1);
	    
	    mPlayProgressBar = (SeekBar) findViewById(R.id.playProgressBar1);
	    mTvPlayMaxPoint = (TextView) findViewById(R.id.tvPlayMaxPoint1);
	    mBtnStartPlay.setOnClickListener(this);
	    mBtnStopPlay.setOnClickListener(this);
		
		final String date = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date(System.currentTimeMillis()));
		final String time = new SimpleDateFormat("HH시 mm분").format(new Date(System.currentTimeMillis()));
		
		final EditText edt3 = (EditText) findViewById(R.id.editText3);
		final EditText edt4 = (EditText) findViewById(R.id.editText4);
		final EditText edt1 = (EditText) findViewById(R.id.editText1);
		final EditText edt2 = (EditText) findViewById(R.id.editText2);
		final DBMS db = new DBMS(getApplicationContext());
		
		img = (ImageView) findViewById(R.id.imageView1);
		
		
		//Button rec = (Button) findViewById(R.id.button1);
		Button pic = (Button) findViewById(R.id.button2);
		Button gal = (Button) findViewById(R.id.button3);
		
		
		
		edt3.setText(date);
		edt4.setText(time);
		
		Button btn = (Button) findViewById(R.id.botton1);
		btn.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				it.putExtra("date", edt3.getText() +"");
				it.putExtra("time", edt4.getText() +"");
				it.putExtra("data", edt1.getText() + "");
				it.putExtra("number", edt2.getText() + "");
				
				if(uri!=null){
					Toast.makeText(getApplicationContext(), uri+"", 1);
				}
				
				img.setDrawingCacheEnabled(true);
				img.buildDrawingCache();
				//이미지 캡쳐
				Bitmap saveBitmap = img.getDrawingCache();
				    
				Log.e("saveImgErr","-------"+saveBitmap+"-------");
				
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
				String namePostfix = format.format(new Date());
				String fname = "mycapture_"+namePostfix;
				//checkPermission(saveBitmap);
				if(writeFile(saveBitmap,fname)){
					Intent it1 = getIntent();
					String title = it1.getExtras().getString("d_title");
					db.dataInput_s("image", fname, title);
				}
				
				it.putExtra("btm", bm);
				setResult(1002, it);
				finish();
				
			}
		});
		
		Button rec = (Button)findViewById(R.id.record);
		
		//녹음
		rec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SuperDetail.this, Rec.class) ;
				startActivity(intent);
				
			}
		});
		
		
		
	
		
		
		
		
		
		
		pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(it, Camera_CALL);
			}
		});
		
		gal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(it,Gal);
			}
		});
	
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case Camera_CALL:
				bm=(Bitmap) data.getExtras().get("data");
				img.setImageBitmap((Bitmap) data.getExtras().get("data"));
				break;
			case Gal:
				try { uri=data.getData();
					img.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}

	}

	public Bitmap rotate_bm(Bitmap bm, int degree) {
		Bitmap rotatebm = null;
		Matrix m = new Matrix(); //이미지 회전시키는 도구
		m.setRotate(degree);
		
		rotatebm = Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),m,true);
		//사진을 자르는 메소드 -> 사진, 시작점x축, 시작점y축, 넓이, 높이, 
		// 							메트릭스객체, 메트릭스 적용 여부
		
		return rotatebm;
	}
	
	
	
	
	public boolean writeFile(Bitmap saveBitmap, String fname){
		boolean success=false;
		String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		String basePath = sdPath+File.separator+"/MyApp"+File.separator;
		File dir = new File(basePath);
		if(!dir.exists()) {
		dir.mkdirs();
		}
		   
		File saveFile = new File(basePath+File.separator+fname+".jpg");
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(saveFile);
			saveBitmap.compress(CompressFormat.JPEG, 70, output);
			Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
			success = true;
		} catch(IOException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			if(output!=null) { try{output.close();}catch(Exception e){e.printStackTrace();}}
		}
		return success;
	}
	
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ녹음된 페이지 불러와서 들려주기ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
	    {
	      case R.id.btnStartPlay1:
	        mBtnStartPlayOnClick();
	        break;
	      case R.id.btnStopPlay1:
	        mBtnStopPlayOnClick();
	        break;
	      default:
	        break;
	    }
	}
	
	private void mBtnStartPlayOnClick()
	  {
	    if (mPlayerState == PLAY_STOP)
	    {
	      mPlayerState = PLAYING;
	      initMediaPlayer();
	      startPlay();
	      updateUI();
	    }
	    else if (mPlayerState == PLAYING)
	    {
	      mPlayerState = PLAY_PAUSE;
	      pausePlay();
	      updateUI();
	    }
	    else if (mPlayerState == PLAY_PAUSE)
	    {
	      mPlayerState = PLAYING;
	      startPlay();
	      updateUI();
	    }
	  }
	private void mBtnStopPlayOnClick()
	  {
	    if (mPlayerState == PLAYING || mPlayerState == PLAY_PAUSE)
	    {
	      mPlayerState = PLAY_STOP;
	      stopPlay();
	      releaseMediaPlayer();
	      updateUI();      
	    }
	  }
	private void initMediaPlayer()
	  {
	    // 미디어 플레이어 생성
	    if (mPlayer == null)
	      mPlayer = new MediaPlayer();
	    else
	      mPlayer.reset();
	    
	    mPlayer.setOnCompletionListener(this);
	    
	    String fullFilePath = "/sdcard/MyApp/Mylittle20151204112054Rec.mp4";
	    Log.i("where are you?",fullFilePath);
	    try
	    {
	      mPlayer.setDataSource(fullFilePath);
	      mPlayer.prepare();   
	      int point = mPlayer.getDuration();
	      mPlayProgressBar.setMax(point);
	      
	      int maxMinPoint = point / 1000 / 60;
	      int maxSecPoint = (point / 1000) % 60;
	      String maxMinPointStr = "";
	      String maxSecPointStr = "";
	      
	      if (maxMinPoint < 10)
	        maxMinPointStr = "0" + maxMinPoint + ":";
	      else
	        maxMinPointStr = maxMinPoint + ":";
	      
	      if (maxSecPoint < 10)
	        maxSecPointStr = "0" + maxSecPoint;
	      else
	        maxSecPointStr = String.valueOf(maxSecPoint);
	      
	      mTvPlayMaxPoint.setText(maxMinPointStr + maxSecPointStr);
	      
	      mPlayProgressBar.setProgress(0);
	    }
	    catch(Exception e)
	    {
	      Log.v("ProgressRecorder", "미디어 플레이어 Prepare Error ==========> " + e);
	    }
	  }
	// 재생 시작
	  private void startPlay()
	  {
	    Log.v("ProgressRecorder", "startPlay().....");
	    
	    try
	    {
	      mPlayer.start();
	      
	      // SeekBar의 상태를 0.1초마다 체크      
	      mProgressHandler2.sendEmptyMessageDelayed(0, 100);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      Toast.makeText(this, "error : " + e.getMessage(), 0).show();
	    }
	  }
	  
	  private void pausePlay()
	  {
	    Log.v("ProgressRecorder", "pausePlay().....");
	    
	    // 재생을 일시 정지하고
	    mPlayer.pause();
	    
	    // 재생이 일시정지되면 즉시 SeekBar 메세지 핸들러를 호출한다.
	    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
	  }
	  
	  private void stopPlay()
	  {
	    Log.v("ProgressRecorder", "stopPlay().....");
	    
	    // 재생을 중지하고
	    mPlayer.stop();
	    
	    // 즉시 SeekBar 메세지 핸들러를 호출한다. 
	    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
	  }
	  
	  private void releaseMediaPlayer()
	  {
	    Log.v("ProgressRecorder", "releaseMediaPlayer().....");
	    mPlayer.release();
	    mPlayer = null;
	    mPlayProgressBar.setProgress(0);
	  }
	  
	  public void onCompletion(MediaPlayer mp)
	  {
	    mPlayerState = PLAY_STOP; // 재생이 종료됨

	    // 재생이 종료되면 즉시 SeekBar 메세지 핸들러를 호출한다.
	    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
	    
	    updateUI();
	  }
	  
	  private void updateUI()
	  {
	    
	    if (mPlayerState == PLAY_STOP)
	    {
	      mBtnStartPlay.setText("Play");
	      mPlayProgressBar.setProgress(0);
	    }
	    else if (mPlayerState == PLAYING)
	      mBtnStartPlay.setText("Pause");
	    else if (mPlayerState == PLAY_PAUSE)
	      mBtnStartPlay.setText("Start");
	  }
	}

	
	
*/
package com.example.slidingsimplesample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.book.db.DBMS;
import com.book.db.Detail_DBMS;

public class SuperDetail extends Activity implements View.OnClickListener, OnCompletionListener {
	private static final int REC_STOP = 0;
	  private static final int RECORDING = 1;
	  private static final int PLAY_STOP = 0;
	  private static final int PLAYING = 1;
	  private static final int PLAY_PAUSE = 2;
	  
	  
	  private MediaPlayer mPlayer = null;
	  private int mRecState = REC_STOP;
	  private int mPlayerState = PLAY_STOP;
	  private SeekBar mRecProgressBar, mPlayProgressBar;
	  private Button mBtnStartRec, mBtnStartPlay, mBtnStopPlay;
	  private String  fullFilePath= null;
	  private TextView mTvPlayMaxPoint;
	  private String voice_file;
	  private int mCurRecTimeMs = 0;
	  private int mCurProgressTimeDisplay = 0;
	  
	  private EditText e_time;
	  private EditText e_date ;
	  private EditText e_content; 
	  private EditText e_sd_page ;
	  
	  
	  // 재생시 SeekBar 처리
	  Handler mProgressHandler2 = new Handler()
	  {
	    public void handleMessage(Message msg)
	    {
	      if (mPlayer == null) return;
	      
	      try
	      {
	        if (mPlayer.isPlaying())
	        {
	          mPlayProgressBar.setProgress(mPlayer.getCurrentPosition());
	          mProgressHandler2.sendEmptyMessageDelayed(0, 100);
	        }
	      }
	      catch (IllegalStateException e)
	      {}
	      catch (Exception e)
	      {}
	    }
	  };
	final int Camera_CALL = 1;
	final int Camera_HD = 2;
	final int Gal = 3;
	final int Voice=4;
	String Path; // 저장경로
    Uri uri=null;
    Bitmap bm=null;
    ImageView img;
	@SuppressLint("SimpleDateFormat") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_super_detail);
		Path = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/attageImage.jpg";
         fullFilePath = "/sdcard/MyApp/Mylittle20151204112054Rec.mp4";
		
	    mBtnStartPlay = (Button) findViewById(R.id.btnStartPlay1);
	    mBtnStopPlay = (Button) findViewById(R.id.btnStopPlay1);
	    
	    mPlayProgressBar = (SeekBar) findViewById(R.id.playProgressBar1);
	    mTvPlayMaxPoint = (TextView) findViewById(R.id.tvPlayMaxPoint1);
	    mBtnStartPlay.setOnClickListener(this);
	    mBtnStopPlay.setOnClickListener(this);
		final String date = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date(System.currentTimeMillis()));
		final String time = new SimpleDateFormat(" HH시 mm분").format(new Date(System.currentTimeMillis()));
		
		  e_time = (EditText) findViewById(R.id.sd_time);
		 e_date = (EditText) findViewById(R.id.sd_date);
		  e_content = (EditText) findViewById(R.id.sd_content);
		  e_sd_page = (EditText) findViewById(R.id.sd_page);
		final DBMS db = new DBMS(getApplicationContext());
		
		img = (ImageView) findViewById(R.id.dtl_img_super);
		
		
		

		//날짜 시간 설정
		e_date.setText(date);
		e_time.setText(time);
       
		
		/*//녹음
		rec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SuperDetail.this, Rec.class) ;
				startActivityForResult(intent,1001);
				
			}
		});*/
		/*Button btn = (Button) findViewById(R.id.botton1);
		btn.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				//it.putExtra("date", edt3.getText() +"");
				//it.putExtra("time", edt4.getText() +"");
				//it.putExtra("data", edt1.getText() + "");
				//it.putExtra("number", edt2.getText() + "");
				
				if(uri!=null){
					Toast.makeText(getApplicationContext(), uri+"", 1);
				}
				
				
				img.setDrawingCacheEnabled(true);
				img.buildDrawingCache();
				//이미지 캡쳐
				Bitmap saveBitmap = img.getDrawingCache();
				    
				Log.e("saveImgErr","-------"+saveBitmap+"-------");
				
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
				String namePostfix = format.format(new Date());
				String fname = "mycapture_"+namePostfix;
				
				Intent it1 = getIntent();
				String title = it1.getExtras().getString("d_title");
				//checkPermission(saveBitmap);
				if(writeFile(saveBitmap,fname)){
					
					String s_date = e_date.getText().toString();
					String s_time = e_time.getText().toString();
					String s_sd_page = e_sd_page.getText().toString();
					String s_content = e_content.getText().toString();
					
					Detail_DBMS dt_db = new Detail_DBMS(getApplicationContext());
					//dataInput(String title, String camera_pic, String record_time, String record_page, String content)
					dt_db.dataInput(title, fname, s_date+s_time, s_sd_page, s_content,voice_file );
					//dt_db.dataInput_s("", val, title);
					
				}
				it.putExtra("sd_title", title);
				it.putExtra("btm", bm);
				setResult(1002, it);
				finish();
				
			}
		});*/
		
		/*pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(it, Camera_CALL);
			}
		});*/
		
		/*gal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(it,Gal);
			}
		});
	
	}*/
	}
	//액션바
		 public boolean onCreateOptionsMenu(Menu menu) {
			   getMenuInflater().inflate(R.menu.super_detail, (android.view.Menu) menu);
		        return true;
		    }
		    public boolean onOptionsItemSelected(MenuItem item) {

		 
		        switch(item.getItemId()){//메뉴실행버튼

		        case R.id.save:
		        	Intent it = new Intent();
					//it.putExtra("date", edt3.getText() +"");
					//it.putExtra("time", edt4.getText() +"");
					//it.putExtra("data", edt1.getText() + "");
					//it.putExtra("number", edt2.getText() + "");
					
					/*if(uri!=null){
						Toast.makeText(getApplicationContext(), uri+"", 1);
					}*/
					
					
					img.setDrawingCacheEnabled(true);
					img.buildDrawingCache();
					//이미지 캡쳐
					Bitmap saveBitmap = img.getDrawingCache();
					    
					Log.e("saveImgErr","-------"+saveBitmap+"-------");
					
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
					String namePostfix = format.format(new Date());
					String fname = "mycapture_"+namePostfix;
					
					Intent it1 = getIntent();
					String title = it1.getExtras().getString("d_title");
					//checkPermission(saveBitmap);
					if(writeFile(saveBitmap,fname)){
						
						String s_date = e_date.getText().toString();
						String s_time = e_time.getText().toString();
						String s_sd_page = e_sd_page.getText().toString();
						String s_content = e_content.getText().toString();
						
						Detail_DBMS dt_db = new Detail_DBMS(getApplicationContext());
						//dataInput(String title, String camera_pic, String record_time, String record_page, String content)
						dt_db.dataInput(title, fname, s_date+s_time, s_sd_page, s_content,voice_file );
						//dt_db.dataInput_s("", val, title);
						
					}
					it.putExtra("sd_title", title);
					it.putExtra("btm", bm);
					setResult(1002, it);
					finish();
					
				
		 
		 			return true;
		        case R.id.camera:
		            
		        	 it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(it, Camera_CALL);
		 			return true;
		        case R.id.gal:
		        	 it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(it, Camera_CALL);
		   		 
		 			return true;
		        case R.id.record:
		        	Intent intent = new Intent(SuperDetail.this, Rec.class) ;
					startActivityForResult(intent,1001);
		   		 
		 			return true;
		             
		        default:
		            return true;
		        }
		   
		    }
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case Camera_CALL:
				bm=(Bitmap) data.getExtras().get("data");
				img.setImageBitmap((Bitmap) data.getExtras().get("data"));
				break;
			case Gal:
				try { uri=data.getData();
					img.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			}
		}
		if(resultCode==123){
		
			voice_file=data.getStringExtra("voice_file");
		}

	}

	public Bitmap rotate_bm(Bitmap bm, int degree) {
		Bitmap rotatebm = null;
		Matrix m = new Matrix(); //이미지 회전시키는 도구
		m.setRotate(degree);
		
		rotatebm = Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),m,true);
		//사진을 자르는 메소드 -> 사진, 시작점x축, 시작점y축, 넓이, 높이, 
		// 							메트릭스객체, 메트릭스 적용 여부
		
		return rotatebm;
	}
	
	
	
	
	
	public boolean writeFile(Bitmap saveBitmap, String fname){
		boolean success=false;
		String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		String basePath = sdPath+File.separator+"/MyAPP"+File.separator;
		File dir = new File(basePath);
		if(!dir.exists()) {
		dir.mkdirs();
		}
		   
		File saveFile = new File(basePath+File.separator+fname+".jpg");
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(saveFile);
			saveBitmap.compress(CompressFormat.JPEG, 70, output);
			Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
			success = true;
		} catch(IOException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			if(output!=null) { try{output.close();}catch(Exception e){e.printStackTrace();}}
		}
		return success;
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ녹음된 페이지 불러와서 들려주기ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		
	
	 
	 
	@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
		    {
		      case R.id.btnStartPlay1:
		        mBtnStartPlayOnClick();
		        break;
		      case R.id.btnStopPlay1:
		        mBtnStopPlayOnClick();
		        break;
		      default:
		        break;
		    }
		}
		
		private void mBtnStartPlayOnClick()
		  {
		    if (mPlayerState == PLAY_STOP)
		    {
		      mPlayerState = PLAYING;
		      initMediaPlayer();
		      startPlay();
		      updateUI();
		    }
		    else if (mPlayerState == PLAYING)
		    {
		      mPlayerState = PLAY_PAUSE;
		      pausePlay();
		      updateUI();
		    }
		    else if (mPlayerState == PLAY_PAUSE)
		    {
		      mPlayerState = PLAYING;
		      startPlay();
		      updateUI();
		    }
		  }
		private void mBtnStopPlayOnClick()
		  {
		    if (mPlayerState == PLAYING || mPlayerState == PLAY_PAUSE)
		    {
		      mPlayerState = PLAY_STOP;
		      stopPlay();
		      releaseMediaPlayer();
		      updateUI();      
		    }
		  }
		private void initMediaPlayer()
		  {
		    // 미디어 플레이어 생성
		    if (mPlayer == null)
		      mPlayer = new MediaPlayer();
		    else
		      mPlayer.reset();
		    
		    mPlayer.setOnCompletionListener(this);
		    
		    String fullFilePath = voice_file;
		    Log.i("where are you?",fullFilePath);
		    try
		    {
		      mPlayer.setDataSource(fullFilePath);
		      mPlayer.prepare();   
		      int point = mPlayer.getDuration();
		      mPlayProgressBar.setMax(point);
		      
		      int maxMinPoint = point / 1000 / 60;
		      int maxSecPoint = (point / 1000) % 60;
		      String maxMinPointStr = "";
		      String maxSecPointStr = "";
		      
		      if (maxMinPoint < 10)
		        maxMinPointStr = "0" + maxMinPoint + ":";
		      else
		        maxMinPointStr = maxMinPoint + ":";
		      
		      if (maxSecPoint < 10)
		        maxSecPointStr = "0" + maxSecPoint;
		      else
		        maxSecPointStr = String.valueOf(maxSecPoint);
		      
		      mTvPlayMaxPoint.setText(maxMinPointStr + maxSecPointStr);
		      
		      mPlayProgressBar.setProgress(0);
		    }
		    catch(Exception e)
		    {
		      Log.v("ProgressRecorder", "미디어 플레이어 Prepare Error ==========> " + e);
		    }
		  }
		// 재생 시작
		  private void startPlay()
		  {
		    Log.v("ProgressRecorder", "startPlay().....");
		    
		    try
		    {
		      mPlayer.start();
		      
		      // SeekBar의 상태를 0.1초마다 체크      
		      mProgressHandler2.sendEmptyMessageDelayed(0, 100);
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		      Toast.makeText(this, "error : " + e.getMessage(), 0).show();
		    }
		  }
		  
		  private void pausePlay()
		  {
		    Log.v("ProgressRecorder", "pausePlay().....");
		    
		    // 재생을 일시 정지하고
		    mPlayer.pause();
		    
		    // 재생이 일시정지되면 즉시 SeekBar 메세지 핸들러를 호출한다.
		    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
		  }
		  
		  private void stopPlay()
		  {
		    Log.v("ProgressRecorder", "stopPlay().....");
		    
		    // 재생을 중지하고
		    mPlayer.stop();
		    
		    // 즉시 SeekBar 메세지 핸들러를 호출한다. 
		    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
		  }
		  
		  private void releaseMediaPlayer()
		  {
		    Log.v("ProgressRecorder", "releaseMediaPlayer().....");
		    mPlayer.release();
		    mPlayer = null;
		    mPlayProgressBar.setProgress(0);
		  }
		  
		  public void onCompletion(MediaPlayer mp)
		  {
		    mPlayerState = PLAY_STOP; // 재생이 종료됨

		    // 재생이 종료되면 즉시 SeekBar 메세지 핸들러를 호출한다.
		    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
		    
		    updateUI();
		  }
		  
		  private void updateUI()
		  {
		    
		    if (mPlayerState == PLAY_STOP)
		    {
		      mBtnStartPlay.setText("Play");
		      mPlayProgressBar.setProgress(0);
		    }
		    else if (mPlayerState == PLAYING)
		      mBtnStartPlay.setText("Pause");
		    else if (mPlayerState == PLAY_PAUSE)
		      mBtnStartPlay.setText("Start");
		  }
		}
	

