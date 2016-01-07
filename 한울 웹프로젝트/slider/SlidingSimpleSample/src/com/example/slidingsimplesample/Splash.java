package com.example.slidingsimplesample;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity {
	
	/*private int i = 0;
    private String com;
    */
	private int lodingtime = 1500;
	
 
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		//onKeyDown(4, null);
	    
	    
		//로딩화면 스레드
		Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
 
            @Override
            public void run() {
                finish();       // 3 초후 이미지를 닫아버림
            }
        }, lodingtime);
	}
    

}
	

