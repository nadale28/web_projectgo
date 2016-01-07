package com.example.slidingsimplesample;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.book.db.DBMS;

public class SubActivity_search extends Activity{
	//���̹� åapi�� �̿��ϱ� ���� �߱޹��� Ű
		String myKey="32bfe562ad483a3ced05ae3ec80f24da";
		//��� å�� �˻��� ������
		String myDisplay="7";
		//�˻��� å �̸�... editText �ڽ����� �Է¹��� ��
		String myQuery="";
		//private final int MY_PERMISSION_REQUEST_STORAGE = 100;
		/*String main_title = null;
		String main_fname = null;*/
		 String bookcase=null;
		@Override
		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_sub_search);
			
			
			final DBMS db = new DBMS(getApplicationContext());
			final ListView lv = (ListView)findViewById(R.id.main_listview);
			final EditText search_box = (EditText)findViewById(R.id.search_box);
			Button search_btn = (Button)findViewById(R.id.search_btn);
			
			
			search_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//�˻�â���� �˻�� �Է¹޴´�
					myQuery = search_box.getText().toString();
					
					String url = "http://openapi.naver.com/search?"
							+"key="+myKey+"&query="+myQuery+"&display="+myDisplay+
								"&start=1&target=book";
					
					//parsingAsynctask ����... ����Ʈ�信 �ѷ���� �ϱ� ������ context�� listview �� �Ѱ��ش�.
					 ParsingTask parsing = new ParsingTask(getApplicationContext(), lv);
					//parsingAsynctask ����
					parsing.execute(url);
				}
			});  
			
			lv.setOnItemClickListener(new OnItemClickListener() {
				//http://blog.naver.com/gojinha/140202787412
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
					final ImageView iv = (ImageView)(((RelativeLayout)view).getChildAt(0)).findViewById(R.id.list_imageview);
					final String title = ((TextView)(((RelativeLayout)view).getChildAt(1)).
										findViewById(R.id.list_title_textview)).getText().toString();
					final String author = ((TextView)(((RelativeLayout)view).getChildAt(2)).
										findViewById(R.id.list_author_textview)).getText().toString();
					BitmapDrawable bitmap = (BitmapDrawable) ((ImageView)((RelativeLayout)view).getChildAt(0)).getDrawable();
					
					LayoutInflater inflater = getLayoutInflater();
					final View dialogView = inflater.inflate(R.layout.dialog_subactivity, null);
					AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity_search.this);
					builder.setView(dialogView);
					builder.setTitle("å �߰�");
					builder.setIcon(android.R.drawable.ic_menu_add);
					ImageView img = (ImageView)dialogView.findViewById(R.id.ds_dialog_img);
					final EditText e_title = (EditText)dialogView.findViewById(R.id.ds_editTitle);
					final EditText e_author = (EditText)dialogView.findViewById(R.id.ds_editAuthor);
					final Spinner e_booklist= (Spinner)dialogView.findViewById(R.id.ds_spinner);
					String[] option_spinner = getResources().getStringArray(R.array.spinner1);
					ArrayAdapter<String> adapter_spinner=new ArrayAdapter<String>(SubActivity_search.this,android.R.layout.simple_spinner_dropdown_item,option_spinner);
					e_booklist.setAdapter(adapter_spinner);
					/*setSpinner(R.id.ds_spinner,android.R.layout.simple_spinner_dropdown_item);*/
					
					
					
					img.setImageBitmap(bitmap.getBitmap());
					e_title.setText(title);
					e_author.setText(author);
					Toast.makeText(getApplicationContext(), bookcase+"å�忡 ����˴ϴ�.",
							Toast.LENGTH_SHORT).show();
					
					builder.setPositiveButton("�����ϱ�", new android.content.DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							bookcase=(String)e_booklist.getSelectedItem();
							// TODO Auto-generated method stub
							
							iv.setDrawingCacheEnabled(true);
							iv.buildDrawingCache();
							//�̹��� ĸ��
							Bitmap saveBitmap = iv.getDrawingCache();
							    
							Log.e("saveImgErr","-------"+saveBitmap+"-------");
							
							SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
							String namePostfix = format.format(new Date());
							String fname = "mybook_"+namePostfix;
							//checkPermission(saveBitmap);
							/*if(writeFile(saveBitmap,fname)){
								db.dataInput(e_title.getText()+"", e_author.getText()+"", fname);
							}*/
							
							//MainActivity���� �˻������� ������... �ش� å������ ������ SubActivity�� �Ѿ�´�.
							String main_title=null;
							String main_fname=null;
							Intent ms_intent = getIntent();
							if(ms_intent.getExtras()!=null){
								 main_title = ms_intent.getExtras().getString("main_title");
								 main_fname = ms_intent.getExtras().getString("main_fname");
							}
							if((main_title!=null)&&writeFile(saveBitmap,main_fname)){
								db.dataUpdate_s("title", e_title.getText()+"", main_title);
								db.dataUpdate_s("author", e_author.getText()+"", main_title);
								db.dataUpdate_s("bookcase", bookcase, main_title);
								
							}else if(writeFile(saveBitmap,fname)){
								Toast.makeText(getApplicationContext(), bookcase,
										Toast.LENGTH_SHORT).show();
								db.dataInput(e_title.getText()+"", e_author.getText()+"",bookcase,/*"12"*/ fname);
								
							}
							Intent intent = new Intent(SubActivity_search.this, MainActivity.class);
							//
							startActivity(intent);
						}
					});
					builder.setNegativeButton("���", new android.content.DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					
					AlertDialog dialog = builder.create();
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
					
				}
			});
		}

		/*@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if(requestCode==2015){
				Intent ms_intent = getIntent();
				 main_title = ms_intent.getExtras().getString("main_title");
				 main_fname = ms_intent.getExtras().getString("main_fname");
			}
		}*/
		
		
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
				/*Toast.makeText(getApplicationContext(), "����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();*/
				success = true;
			} catch(IOException e) {
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			} finally {
				if(output!=null) { try{output.close();}catch(Exception e){e.printStackTrace();}}
			}
			return success;
		}
	
	   public boolean onCreateOptionsMenu(Menu menu) {
		   getMenuInflater().inflate(R.menu.sub, (android.view.Menu) menu);
	        return true;
	    }
	    public boolean onOptionsItemSelected(MenuItem item) {

	 
	        switch(item.getItemId()){//�޴������ư
	        case android.R.id.home:
				String text = "����ȭ������ ���ư��ϴ�.";
	            Toast.makeText(SubActivity_search.this, text, Toast.LENGTH_SHORT).show();
	           
	          //���ư ������ �ڷΰ��� ��
			
	            Intent intent = new Intent(this, MainActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	 
	 			return true;

	             
	        default:
	            return true;
	        }
	   
	    }
		
}

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_search);
        getActionBar().setDisplayShowTitleEnabled(true);
        //Ÿ��Ʋ�̸����̰�
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
		final ListView lv = (ListView)findViewById(R.id.listView1);
		final EditText search_box = (EditText)findViewById(R.id.search_box);
		Button  search_btn = (Button)findViewById(R.id.search_btn);
		
		final DBManager dbManager = new DBManager(getApplicationContext(),
				"Item.db", null, 1);
		
		list = new ArrayList<String>();
		list = dbManager.selectAll(list);
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.mylist_search, list);
		lv.setAdapter(adapter);
		
		//title_view = (TextView)findViewById(R.id.list_title_textview);
			
		
		
		search_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//�˻�â���� �˻�� �Է¹޴´�
				myQuery = search_box.getText().toString();
				
				String url = "http://openapi.naver.com/search?"
						+"key="+myKey+"&query="+myQuery+"&display="+myDisplay+
							"&start=1&target=book";
				
				//parsingAsynctask ����... ����Ʈ�信 �ѷ���� �ϱ� ������ context�� listview �� �Ѱ��ش�.
				 ParsingTask parsing = new ParsingTask(getApplicationContext(), lv);
				//parsingAsynctask ����
				parsing.execute(url);
						
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			//	Toast.makeText(getApplicationContext(), "text", 1).show();;
				LayoutInflater inflater = getLayoutInflater();
			
				final View dialogView = inflater.inflate(R.layout.dialog_addmember,
						null);
				AlertDialog.Builder buider = new AlertDialog.Builder(SubActivity_search.this);  //���̾�α� ��ü
				String text = ((TextView)((RelativeLayout)view).getChildAt(1)).getText().toString();
				BitmapDrawable bitmap = (BitmapDrawable) ((ImageView)((RelativeLayout)view).getChildAt(0)).getDrawable();
				
				
				buider.setTitle("å ����");
				//BitmapDrawable d = (BitmapDrawable)((ImageView) findViewById(R.id.imageView1)).getDrawable();
				//Bitmap b = d.getBitmap();
				//buider.setIcon(android.R.drawable.ic_menu_add); // �̹���
				buider.setView(dialogView);
				ImageView img = (ImageView)dialogView.findViewById(R.id.imageView1);
				img.setImageBitmap(bitmap.getBitmap());
				EditText ed = (EditText)dialogView.findViewById(R.id.dialog_edit);
				ed.setText(text);
				
				buider.setPositiveButton("����", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						//if (et.getText() != null) {
							dbManager.insert(getText()+"");
							list = dbManager.selectAll(list);
							adapter.notifyDataSetChanged();
							et.setText("");
						//}
						
					}							
				});
				
				
						
				buider.setNegativeButton("���", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				AlertDialog dialog = buider.create();

				dialog.setCanceledOnTouchOutside(false);// ���̾�α� �ۿ� ��ġ�ص� �Ȳ�����
				dialog.show();
				
			}
			
			
		});
		

		
		
	}*/
    


