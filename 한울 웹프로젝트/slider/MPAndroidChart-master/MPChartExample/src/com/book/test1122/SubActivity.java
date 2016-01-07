package com.book.test1122;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.book.db.DBHelper;
import com.book.db.DBMS;
import com.example.test1122.R;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SubActivity extends Activity {
	//���̹� åapi�� �̿��ϱ� ���� �߱޹��� Ű
	String myKey="32bfe562ad483a3ced05ae3ec80f24da";
	//��� å�� �˻��� ������
	String myDisplay="7";
	//�˻��� å �̸�... editText �ڽ����� �Է¹��� ��
	String myQuery="";
	//private final int MY_PERMISSION_REQUEST_STORAGE = 100;
	/*String main_title = null;
	String main_fname = null;*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		
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
				AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
				builder.setView(dialogView);
				ImageView img = (ImageView)dialogView.findViewById(R.id.ds_dialog_img);
				final EditText e_title = (EditText)dialogView.findViewById(R.id.ds_editTitle);
				final EditText e_author = (EditText)dialogView.findViewById(R.id.ds_editAuthor);
				
				
				img.setImageBitmap(bitmap.getBitmap());
				e_title.setText(title);
				e_author.setText(author);
				
				builder.setPositiveButton("����", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
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
							
						}else if(writeFile(saveBitmap,fname)){
							db.dataInput(e_title.getText()+"", e_author.getText()+"", fname);
						}
						Intent intent = new Intent(SubActivity.this, MainActivity.class);
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
			Toast.makeText(getApplicationContext(), "����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			success = true;
		} catch(IOException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			if(output!=null) { try{output.close();}catch(Exception e){e.printStackTrace();}}
		}
		return success;
	}
}
