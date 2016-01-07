package com.example.slidingsimplesample;
 
import java.util.ArrayList;
import com.book.db.DBMS;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
 
public class Fragment3 extends Fragment {
	MainAdapter adapter;
	ArrayList<Book_info> myBookList;
		private final int MY_PERMISSION_REQUEST_STORAGE = 100;
		boolean check = false;
	    //private TextView mResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3, container, false);
       
        
        	        		
        	        final DBMS db = new DBMS(getActivity());
        	        GridView gv = (GridView)v.findViewById(R.id.gridView1);

        	        gv.setOnItemClickListener(new OnItemClickListener() {

        				@Override
        				public void onItemClick(AdapterView<?> parent, View view,
        						int position, long id) {
        					TextView  title = (TextView)(((LinearLayout)view).getChildAt(1)).
        							findViewById(R.id.grid_title);
        					Intent intent = new Intent(getActivity(), DetailActivity.class);
        					
        					
        					intent.putExtra("title", title.getText().toString());
        					startActivity(intent);
        				}
        			});
        	        
        	        gv.setOnItemLongClickListener(new OnItemLongClickListener() {

        				@Override
        				public boolean onItemLongClick(AdapterView<?> parent,
        						View view, final int position, long id) {
        					// TODO Auto-generated method stub
        	
        					LayoutInflater inflater = getActivity().getLayoutInflater();
        					View dialogView = inflater.inflate(R.layout.dialog_main1, null);
        					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        					builder.setView(dialogView);
        					
        					final TextView title = (TextView)(((LinearLayout)view).getChildAt(1)).
        							findViewById(R.id.grid_title);
        					final String title_name = title.getText().toString();
        					LinearLayout search_c = (LinearLayout)dialogView.findViewById(R.id.dm_search);
        					LinearLayout direct_c = (LinearLayout)dialogView.findViewById(R.id.dm_direct);
        					LinearLayout move_bc = (LinearLayout)dialogView.findViewById(R.id.dm_movebc);
        					LinearLayout delete_c = (LinearLayout)dialogView.findViewById(R.id.dm_delete);
        					
        					final Book_info book = db.dataSelect(title_name);
        					
        					//book = db.dataSelect(title_name);
        					
        					final AlertDialog dialog = builder.create();
        					dialog.setCanceledOnTouchOutside(true);
        					dialog.show();
        					
        					direct_c.setOnClickListener(new OnClickListener() {
        						
        						@Override
        						public void onClick(View v) {
        							dialog.dismiss();
        							
        							LayoutInflater inflater_c = getActivity().getLayoutInflater();
        							View dialogView_c = inflater_c.inflate(R.layout.dialog_subactivity, null);
        							AlertDialog.Builder builder_c = new AlertDialog.Builder(getActivity());
        							builder_c.setView(dialogView_c);
        							
        							final EditText de_title= (EditText)dialogView_c.findViewById(R.id.ds_editTitle);
        							final EditText de_author = (EditText)dialogView_c.findViewById(R.id.ds_editAuthor);
        							ImageView de_img = (ImageView)dialogView_c.findViewById(R.id.ds_dialog_img);
        							
        							
        							
        							de_title.setText(book.getTitle());
        							de_author.setText(book.getAuthor());
        							
        							GetBitmap gb = new GetBitmap();
        							Bitmap bm = gb.getBitmap(book.getImage_source());
        							de_img.setImageBitmap(bm);
        							
        							builder_c.setPositiveButton("변경", new android.content.DialogInterface.OnClickListener() {
        								
        								@Override
        								public void onClick(DialogInterface dialog, int which) {
        									// TODO Auto-generated method stub
        									db.dataUpdate_s("title", de_title.getText().toString(), title_name);
        									db.dataUpdate_s("author", de_author.getText().toString(), title_name);
        									
        									if(title!=null){
        										title.setText(de_title.getText().toString());
        									}
        								}
        							});
        							
        							builder_c.setNegativeButton("취소", new android.content.DialogInterface.OnClickListener() {
        								
        								@Override
        								public void onClick(DialogInterface dialog, int which) {
        									// TODO Auto-generated method stub
        									return;
        								}
        							});
        							
        							AlertDialog dialog_c = builder_c.create();
        							dialog_c.setCanceledOnTouchOutside(true);
        							dialog_c.show();
        						}
        					});
        					
        					search_c.setOnClickListener(new OnClickListener() {
        						
        						@Override
        						public void onClick(View v) {
        							// TODO Auto-generated method stub
        							Intent intent = new Intent(getActivity(), SubActivity_search.class);
        							intent.putExtra("main_title", title_name);
        							intent.putExtra("main_fname", book.getImage_source());
        							startActivityForResult(intent,2015);
        						}
        					});
        					
        					delete_c.setOnClickListener(new OnClickListener() {
        						
        						@Override
        						public void onClick(View v) {
        							Log.i("삭제", title_name);
        							db.dataDelete(title_name);
        							myBookList.remove(position);
        		        			adapter.notifyDataSetChanged();
        							dialog.dismiss();
        						}
        					});
        					//return true를 하면 click메소드와 long click 중복이 안됨
        					return true;
        					
        					
        				}
        			});
        	        
        	        //checkPermission();
        	        
        	       // if(check){
        	        	myBookList = new ArrayList<Book_info>();
        	        	myBookList = db.dataselect_booklist("사회과학");
        	        	/*<item>사회과학</item>
        	            <item>문학소설</item>
        	            <item>판타지</item>*/
        	        	
        	        	if(myBookList.size()!=0){
        	        				 adapter = new MainAdapter(getActivity(), 
        	        				 myBookList, R.layout.main_grid);
        	        		gv.setAdapter(adapter);
        	        	}
        	        //}
        	        	return v;
        	    }
        	    
        	    
        	    //http://mytalkhome.tistory.com/852	  
        	   // http://thdev.net/634
        	  //  @TargetApi(23)
        		/*private void checkPermission() {
        		    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        		            != PackageManager.PERMISSION_GRANTED
        		            || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        		            != PackageManager.PERMISSION_GRANTED) {

        		        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        		        	//사용자가 권한 취소했을시 재요청
        		        	requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, 
        			        		Manifest.permission.WRITE_EXTERNAL_STORAGE},
        			                MY_PERMISSION_REQUEST_STORAGE);
        		        }
        		        
        		        //최초 권한요청
        		        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, 
        		        		Manifest.permission.WRITE_EXTERNAL_STORAGE},
        		                MY_PERMISSION_REQUEST_STORAGE);

        		    } else {//권한있음이 확인되었을때
        		    		check=true;
        		    	}
        		    }
        		
        	    
        	    @Override
        	    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        	        switch (requestCode) {
        	            case MY_PERMISSION_REQUEST_STORAGE:
        	                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
        	                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        	                	//권한 동의 버튼을 선택했을때
        	                	check=true;
        	                } else {
        	                	Toast.makeText(getApplicationContext(), "권한사용에 동의해주세야 이용 가능", 1).show();
        	                	finish();
        	                }
        	                break;
        	        }
        	    }*/
        	    
        	    
        	    
        	    
        	
    
    }
