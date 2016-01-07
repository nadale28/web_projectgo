package com.example.slidingsimplesample;
 

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.book.db.DBMS;
 
public class Fragment1 extends Fragment {
	
	private View v;
	ListView lv;
	ArrayList<String> list;
	ArrayAdapter<String> adapter;
	private DBMS db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	v = inflater.inflate(R.layout.fragment1, container, false);

    	
    	/*ImageButton ibtn=(ImageButton)v.findViewById(R.id.imageButton1);*/
    	Button graph=(Button)v.findViewById(R.id.button1);
    	/*TextView tv_font1 =(Textview)v.findViewById(R.id.tv_font1);
    	title_tv.setTypeface(Typeface.createFromAsset(getAssets(),"Frutiger55Roman.ttf"));*/
		/*ibtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(getActivity(),SubActivity_search.class);
				startActivityForResult(it,1001);
				 String text = "검색화면으로 이동합니다.";
				 db = new DBMS(getActivity());
			     int count_regdate= db.datacount("문학소설");
			    String count_regdate= db.datacount("문학소설"); db테스트주석
			     ---------------------------------------------------------
                 Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                 

			}
		});//버튼클릭시 서브 액티비티로 이동~
*/   
		graph.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(getActivity(),HorizontalBarChartActivity.class);
				startActivityForResult(it,1001);
				 String text = "통계화면으로 이동합니다.";
                 Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                 

			}
		});//버튼클릭시 서브 액티비티로 이동~
        
        return v; 
        }

	/*@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		String result = data.getStringExtra("data");
		((TextView)v.findViewById(R.id.textView1)).setText(result);
	}//SUB액티비티에서 데이터 받는 메소드!
*/    
    
        
  /* public void changeView(String data){
	   ;
   }
*/

    
}
