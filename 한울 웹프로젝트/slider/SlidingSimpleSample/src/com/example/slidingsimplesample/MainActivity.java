package com.example.slidingsimplesample;
 


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * ������ ���̾ƿ��� �̿��� �� ���� �並 ��ø��Ű�� ���ü� �Ӽ��� �̿��� ���� �ٲٸ鼭 �����ݴϴ�.
 * 
 * @author Mike
 *
 */
@SuppressLint("NewApi")
public class MainActivity extends BaseActivity {
/*	int splash=0;*/
	/*Fragment1 frag1;*/
	
	//�Ʒ��� ����ȭ���� �����ϴ� �޼ҵ�
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*TextView tv_font1 =(TextView)findViewById(R.id.tv_font1);
    	tv_font1.setTypeface(Typeface.createFromAsset(getAssets(),"font1.ttf"));
    	TextView tv_font2 =(TextView)findViewById(R.id.tv_font2);
    	tv_font2.setTypeface(Typeface.createFromAsset(getAssets(),"font2.ttf"));
    	TextView tv_font3 =(TextView)findViewById(R.id.tv_font3);
    	tv_font3.setTypeface(Typeface.createFromAsset(getAssets(),"font1.ttf"));*/
    	
        /*if(splash==0){*/
        /*startActivity(new Intent(this, Splash.class));*/
        /*splash++;}*/
        fragmentReplace(0);
        
        
       
        getActionBar().setDisplayShowTitleEnabled(true);
        
        //Ÿ��Ʋ�̸����̰�
        getActionBar().setDisplayHomeAsUpEnabled(false);
        
        //Ÿ��Ʋ ȨŰ ���̰�
        /*intent = new Intent(this, Activity3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
/*        ImageButton ibtn=(ImageButton)findViewById(R.id.imageButton1);
       
		ibtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MainActivity.this,SubActivity.class);
				startActivityForResult(it,1001);
				 String text = "Action item, icon only, always displayed";
                 Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

			}
		});*/

    }
	//�Ʒ��� �ɼǸ޴��� res/menu/activity_main.xml �̶�� �޴�������ȭ���� 

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==1002){
			int data1=Integer.parseInt(data.getStringExtra("flag"));
	 
	        	fragmentReplace(data1);
	        
		
		}
	}

    
}