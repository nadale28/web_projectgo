package com.example.slidingsimplesample;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		getActionBar().setDisplayShowTitleEnabled(true);//타이틀보여주기
        getActionBar().setDisplayHomeAsUpEnabled(true);//액션바뒤로키 사용
         final ActionBar actionBar = getActionBar();
		Button btn=(Button)findViewById(R.id.button1);
		final EditText edt=(EditText)findViewById(R.id.editText1);
		actionBar.setCustomView(R.layout.actionbar_view);
        EditText search = (EditText) actionBar.getCustomView().findViewById(
                R.id.searchfield);
        
        search.setOnKeyListener(new OnKeyListener() {
 
            public boolean onKey(TextView v, int actionId,
                    KeyEvent event) {
                Toast.makeText(SubActivity.this, "Search triggered",
                        Toast.LENGTH_LONG).show();
                return false;
            }

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
        });
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);
    
    
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {//버튼클릭하면 실행 인텐트
				// TODO Auto-generated method stub
				String text = "Action item, icon only, always displayed";
                Toast.makeText(SubActivity.this, text, Toast.LENGTH_SHORT).show();

				String data=edt.getText().toString();
				Intent it = new Intent();
				it.putExtra("data", data);
				setResult(1002,it);
				finish();
			}
		});
	}

	}
