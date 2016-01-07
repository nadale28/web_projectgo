package com.example.slidingsimplesample;
 

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
 
public class BaseActivity extends SlidingFragmentActivity {
 
    protected ListFragment mFrag;
 
    public BaseActivity() {
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
 
        // set the Behind View
        setBehindContentView(R.layout.menu_frame);
        if (savedInstanceState == null) {
            FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
            mFrag = new MenuListFragment();
            t.replace(R.id.menu_frame, mFrag);
            t.commit();
        } else {
            mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
        }
 
        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
 
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//가상 책 클릭 시 검색창으로 이동
    	 String text=null;
        switch (item.getItemId()) {
       
            case R.id.overflow:
                toggle();
                return true;
                 
            case R.id.item1:
            	 text = "검색화면으로 이동합니다.";
                 Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                 Intent it= new Intent(BaseActivity.this,SubActivity_search.class);
         		
     			startActivity(it);
     			finish();

     			return super.onOptionsItemSelected(item);
            /*case R.id.menu_search:
                text = "책 검색을 시작합니다.";
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                return true;*/
                 
                 
            default:
                return super.onOptionsItemSelected(item);
                
            }
     
        }
    
       
    
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.sub, menu);
    	    return super.onCreateOptionsMenu(menu);
    }
     
 
    public void fragmentReplace(int reqNewFragmentIndex) {
          
        Fragment newFragment = null;
  
        newFragment = getFragment(reqNewFragmentIndex);
  
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
  
        transaction.replace(R.id.fragment_mainContainer, newFragment);
  
        getSlidingMenu().showContent();
        transaction.commit();
    }
     
    private Fragment getFragment(int idx) {
        Fragment newFragment = null;
  
        switch (idx) {
        case 0:
            newFragment = new Fragment1();
            break;
        case 1:
        	
            newFragment = new Fragment2();
            break;
        case 2:
            newFragment = new Fragment3();
            break;
        case 3:
            newFragment = new Fragment4();
            break;
        case 4:
            newFragment = new Fragment5();
            break;
        default:
            break;
        }
  
        return newFragment;
    }
    
}