package com.example.slidingsimplesample;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Record implements OnCompletionListener {

	 private MediaPlayer mPlayer = null;
	  private int mRecState = REC_STOP;
	  private int mPlayerState = PLAY_STOP;
	  private SeekBar mRecProgressBar, mPlayProgressBar;
	  private Button mBtnStartRec, mBtnStartPlay, mBtnStopPlay;
	  private String  fullFilePath= null;
	  private TextView mTvPlayMaxPoint;
	  //private String voice_file;
	  private int mCurRecTimeMs = 0;
	  private int mCurProgressTimeDisplay = 0;
	  String vname;
	  public Record(String vname, Button mBtnStartPlay, Button mBtnStopPlay, SeekBar mPlayProgressBar) {
		this.vname = vname;
		this.mBtnStartPlay = mBtnStartPlay;
		this.mBtnStopPlay = mBtnStopPlay;
		this.mPlayProgressBar = mPlayProgressBar;
	}
	  
	  
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
	
	  private static final int REC_STOP = 0;
	  private static final int RECORDING = 1;
	  private static final int PLAY_STOP = 0;
	  private static final int PLAYING = 1;
	  private static final int PLAY_PAUSE = 2;
	
	
	
	public void mBtnStartPlayOnClick()
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
	public void mBtnStopPlayOnClick()
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
	    
	    String fullFilePath = vname;
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
	      //Toast.makeText(this, "error : " + e.getMessage(), 0).show();
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
