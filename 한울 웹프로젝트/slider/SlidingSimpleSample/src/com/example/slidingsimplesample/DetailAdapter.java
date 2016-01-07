package com.example.slidingsimplesample;

import java.util.ArrayList;


import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class DetailAdapter extends BaseAdapter implements OnClickListener,
		OnCompletionListener {

	private LayoutInflater inflater;
	private int layout;
	private Context context;
	private ArrayList<Book_info_detail> book_info_dt;
	private String[] fname;
	ArrayList<ViewHolder> list=new ArrayList<DetailAdapter.ViewHolder>();

	// ³ë±Ý
	/*private static final int REC_STOP = 0;
	private static final int RECORDING = 1;
	private static final int PLAY_STOP = 0;
	private static final int PLAYING = 1;
	private static final int PLAY_PAUSE = 2;

	private MediaPlayer mPlayer = null;
	private int mPlayerState = PLAY_STOP;
	private String fullFilePath = null;
	private String voice_file;*/
	//ViewHolder holder_m;
	//ViewHolder holder;
	//String vname;
	ViewHolder holder_m;
	ViewHolder holder;

	public DetailAdapter(Context context, int layout,
			ArrayList<Book_info_detail> book_info_dt) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.layout = layout;
		this.book_info_dt = book_info_dt;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return book_info_dt.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return book_info_dt.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private static class ViewHolder {
		public TextView time, content, page;
		public int position;
		public ImageView img;
		public SeekBar mPlayProgressBar;
		public Button mBtnStartPlay, mBtnStopPlay;
		public String vname;
		
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//ViewHolder holder;
		// TODO Auto-generated method stub
		final int p=position;
		if (convertView == null) {
			convertView = inflater.inflate(layout, parent, false);
		}
			holder = new ViewHolder();
		//	holder_m = new ViewHolder();
			
			
			holder.time = (TextView) convertView.findViewById(R.id.dtl_time);
			holder.content = (TextView) convertView
					.findViewById(R.id.dtl_content);
			holder.page = (TextView) convertView.findViewById(R.id.dtl_page);
			holder.img = (ImageView) convertView.findViewById(R.id.dtl_img);
			holder.mBtnStartPlay = (Button) convertView.findViewById(R.id.dtl_v_start);
			holder.mBtnStopPlay = (Button) convertView.findViewById(R.id.dtl_v_stop);
			holder.mPlayProgressBar = (SeekBar) convertView.findViewById(R.id.dtl_seekBar);
			//holder_m.mBtnStartPlay = holder.mBtnStartPlay;
			//holder_m.mBtnStopPlay = holder.mBtnStopPlay;
			//holder_m.mPlayProgressBar = holder.mPlayProgressBar;

		//	convertView.setTag(holder);
		//} else {
		//	holder = (ViewHolder) convertView.getTag();
		//}
		holder.position = position;

		
		String time_s = book_info_dt.get(position).getRecord_time();
		String content_s = book_info_dt.get(position).getRecord_content();
		int page_s = book_info_dt.get(position).getRecord_page();
		String fname = book_info_dt.get(position).getCamera_pic();
		String vname = book_info_dt.get(position).getVoice_file();
		Log.v("asdasd",book_info_dt.get(position).getVoice_file());

		//if(vname!=null)
		holder.time.setText(time_s);
		holder.content.setText("-> "+content_s);
		holder.page.setText("page:"+page_s + "");

		Log.i("page", page_s + "");
		Log.i("content", content_s);
		
		
		if(fname!=null)
		Log.i(position+"fname", fname);
		if(vname!=null)
		Log.i(position+"vname", vname);
		
		
		if (fname != null && vname == null) {
			Log.i(position+"fname", fname);
			GetBitmap gb = new GetBitmap();
			Bitmap bm = gb.getBitmap(fname);
			holder.img.setImageBitmap(bm);
			holder.mBtnStartPlay.setVisibility(View.INVISIBLE);
			holder.mBtnStopPlay.setVisibility(View.INVISIBLE);
			holder.mPlayProgressBar.setVisibility(View.INVISIBLE);
			
		}
		else if (vname != null && fname == null) {
			Log.i(position+"fname", fname);
			holder.mBtnStartPlay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.i("vname", holder.vname);
					Log.i("dkdkdk", "dsafsdaklfjslkadflkasdjfl;kasd");
					Record rc=null;
					for(ViewHolder v1 : list){
						if(v1.position==p)
							rc = new Record(v1.vname,v1.mBtnStartPlay, v1.mBtnStopPlay, v1.mPlayProgressBar);
					}
					rc.mBtnStartPlayOnClick();
					
					
				}
			});
			
			holder.mBtnStopPlay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.i("vname", holder.vname);
					Log.i("dkdkdk", "dsafsdaklfjslkadflkasdjfl;kasd");
					Record rc=null;
					for(ViewHolder v1 : list){
						if(v1.position==p)
							rc = new Record(v1.vname,v1.mBtnStartPlay, v1.mBtnStopPlay, v1.mPlayProgressBar);
					}
					rc.mBtnStopPlayOnClick();
				}
			});
			holder.img.setVisibility(View.INVISIBLE);
			
			holder.vname = vname;
			
		}else{
			GetBitmap gb = new GetBitmap();
			Bitmap bm = gb.getBitmap(fname);
			holder.img.setImageBitmap(bm);

			holder.mBtnStartPlay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.i("vname", holder.vname);
					Log.i("dkdkdk", "dsafsdaklfjslkadflkasdjfl;kasd");
					Record rc=null;
					for(ViewHolder v1 : list){
						if(v1.position==p)
							rc = new Record(v1.vname,v1.mBtnStartPlay, v1.mBtnStopPlay, v1.mPlayProgressBar);
					}
					
					rc.mBtnStartPlayOnClick();
					
					
				}
			});
			holder.mBtnStopPlay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.i("vname", holder.vname);
					Log.i("dkdkdk", "dsafsdaklfjslkadflkasdjfl;kasd");
					Record rc=null;
					for(ViewHolder v1 : list){
						if(v1.position==p)
							rc = new Record(v1.vname,v1.mBtnStartPlay, v1.mBtnStopPlay, v1.mPlayProgressBar);
					}
					
					rc.mBtnStopPlayOnClick();
				}
			});
			
			holder.vname = vname;
		}
		list.add(holder);

		return convertView;
	}

	


	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	

}
