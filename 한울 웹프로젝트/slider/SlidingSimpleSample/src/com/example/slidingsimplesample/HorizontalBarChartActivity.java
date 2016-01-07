package com.example.slidingsimplesample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.book.db.DBMS;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.filter.Approximator;
import com.github.mikephil.charting.data.filter.Approximator.ApproximatorType;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HorizontalBarChartActivity extends DemoBase implements
		OnSeekBarChangeListener, OnChartValueSelectedListener {

	protected HorizontalBarChart mChart;
	/* private SeekBar mSeekBarX, mSeekBarY; */
	/* private TextView tvX, tvY; */
	ArrayList<Book_info> myBookList;
	private DBMS db;
	private Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_horizontalbarchart);
		getActionBar().setDisplayShowTitleEnabled(true);

		db = new DBMS(HorizontalBarChartActivity.this);
		// 타이틀이름보이게
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// 타이틀 홈키 보이게
		getActionBar().setHomeButtonEnabled(true);// 버튼사용가능
		/*
		 * tvX = (TextView) findViewById(R.id.tvXMax); tvY = (TextView)
		 * findViewById(R.id.tvYMax);
		 */

		/*
		 * mSeekBarX = (SeekBar) findViewById(R.id.seekBar1); mSeekBarY =
		 * (SeekBar) findViewById(R.id.seekBar2);
		 */

		mChart = (HorizontalBarChart) findViewById(R.id.chart1);
		mChart.setOnChartValueSelectedListener(this);
		// mChart.setHighlightEnabled(false);

		mChart.setDrawBarShadow(false);

		mChart.setDrawValueAboveBar(true);

		mChart.setDescription("");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60);

		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);

		// draw shadows for each bar that show the maximum value
		// mChart.setDrawBarShadow(true);

		// mChart.setDrawXLabels(false);

		mChart.setDrawGridBackground(false);

		// mChart.setDrawYLabels(false);

		tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

		XAxis xl = mChart.getXAxis();
		xl.setPosition(XAxisPosition.BOTTOM);
		xl.setTypeface(tf);
		xl.setDrawAxisLine(true);
		xl.setDrawGridLines(true);
		xl.setGridLineWidth(0.3f);

		YAxis yl = mChart.getAxisLeft();
		yl.setTypeface(tf);
		yl.setDrawAxisLine(true);
		yl.setDrawGridLines(true);
		yl.setGridLineWidth(0.3f);
		// yl.setInverted(true);

		YAxis yr = mChart.getAxisRight();
		yr.setTypeface(tf);
		yr.setDrawAxisLine(true);
		yr.setDrawGridLines(false);
		// yr.setInverted(true);

		setData(12, 50);
		mChart.animateY(2500);

		/*
		 * // setting data mSeekBarY.setProgress(50); mSeekBarX.setProgress(12);
		 * 
		 * mSeekBarY.setOnSeekBarChangeListener(this);
		 * mSeekBarX.setOnSeekBarChangeListener(this);
		 */

		Legend l = mChart.getLegend();
		l.setPosition(LegendPosition.BELOW_CHART_LEFT);
		l.setFormSize(8f);
		l.setXEntrySpace(4f);

		// mChart.setDrawLegend(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bar, menu);
		/* getMenuInflater().inflate(R.menu.sub, menu); */
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home: {
			Toast.makeText(this, "메인화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show();
			Intent it = new Intent(HorizontalBarChartActivity.this,
					MainActivity.class);
			startActivity(it);
			finish();

			break;
		}

		case R.id.actionToggleValues: {
			for (DataSet<?> set : mChart.getData().getDataSets())
				set.setDrawValues(!set.isDrawValuesEnabled());

			mChart.invalidate();
			break;
		}
		case R.id.actionToggleHighlight: {
			if (mChart.getData() != null) {
				mChart.getData().setHighlightEnabled(
						!mChart.getData().isHighlightEnabled());
				mChart.invalidate();
			}
			break;
		}
		case R.id.actionTogglePinch: {
			if (mChart.isPinchZoomEnabled())
				mChart.setPinchZoom(false);
			else
				mChart.setPinchZoom(true);

			mChart.invalidate();
			break;
		}
		case R.id.actionToggleAutoScaleMinMax: {
			mChart.setAutoScaleMinMaxEnabled(!mChart.isAutoScaleMinMaxEnabled());
			mChart.notifyDataSetChanged();
			break;
		}
		case R.id.actionToggleHighlightArrow: {
			if (mChart.isDrawHighlightArrowEnabled())
				mChart.setDrawHighlightArrow(false);
			else
				mChart.setDrawHighlightArrow(true);
			mChart.invalidate();
			break;
		}
		case R.id.actionToggleStartzero: {
			mChart.getAxisLeft().setStartAtZero(
					!mChart.getAxisLeft().isStartAtZeroEnabled());
			mChart.getAxisRight().setStartAtZero(
					!mChart.getAxisRight().isStartAtZeroEnabled());
			mChart.invalidate();
			break;
		}
		case R.id.animateX: {
			mChart.animateX(3000);
			break;
		}
		case R.id.animateY: {
			mChart.animateY(3000);
			break;
		}
		case R.id.animateXY: {

			mChart.animateXY(3000, 3000);
			break;
		}
		case R.id.actionToggleFilter: {

			Approximator a = new Approximator(ApproximatorType.DOUGLAS_PEUCKER,
					25);

			if (!mChart.isFilteringEnabled()) {
				mChart.enableFiltering(a);
			} else {
				mChart.disableFiltering();
			}
			mChart.invalidate();
			break;
		}
		case R.id.actionSave: {
			if (mChart.saveToGallery("title" + System.currentTimeMillis(), 50)) {
				Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
						Toast.LENGTH_SHORT).show();
			} else
				Toast.makeText(getApplicationContext(), "Saving FAILED!",
						Toast.LENGTH_SHORT).show();
			break;
		}
		}
		return true;
	}

	/*
	 * @Override public void onProgressChanged(SeekBar seekBar, int progress,
	 * boolean fromUser) {
	 * 
	 * tvX.setText("" + (mSeekBarX.getProgress() + 1)); tvY.setText("" +
	 * (mSeekBarY.getProgress()));
	 * 
	 * setData(mSeekBarX.getProgress() + 1, mSeekBarY.getProgress());
	 * mChart.invalidate(); }
	 * 
	 * @Override public void onStartTrackingTouch(SeekBar seekBar) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void onStopTrackingTouch(SeekBar seekBar) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

	private void setData(int count, float range) {

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		ArrayList<String> xVals = new ArrayList<String>();
		String count_regdate;

		/* Log.i("확인", count_regdate); */
		int data_count = 0;

		for (int i = 0; i < count; i++) {
			if (i == 1) {
				xVals.add(mMonths[i % 12]);
				count_regdate = db.datacount("2015/1/");
				if (count_regdate == null) {
					data_count = 0;
				}
				data_count = Integer.parseInt(count_regdate);
				yVals1.add(new BarEntry(data_count, i));
			} else {
				xVals.add(mMonths[i % 12]);
				count_regdate = db.datacount("2015/" + i + "");
				Log.i("what is i?", i + "");
				Log.i("what is count?", count_regdate);
				if (count_regdate == null) {
					data_count = 0;
				}
				data_count = Integer.parseInt(count_regdate);
				yVals1.add(new BarEntry(data_count, i));
			}
		}

		/*
		 * count_regdate= db.datacount("0"); yVals1.add(new BarEntry(data_count,
		 * 0)); count_regdate= db.datacount("1"); yVals1.add(new
		 * BarEntry(data_count, 0)); count_regdate= db.datacount("2");
		 * yVals1.add(new BarEntry(data_count, 0)); count_regdate=
		 * db.datacount("3"); yVals1.add(new BarEntry(data_count, 0));
		 * count_regdate= db.datacount("4"); yVals1.add(new BarEntry(data_count,
		 * 0)); count_regdate= db.datacount("5"); yVals1.add(new
		 * BarEntry(data_count, 0)); count_regdate= db.datacount("6");
		 * yVals1.add(new BarEntry(data_count, 0)); count_regdate=
		 * db.datacount("7"); yVals1.add(new BarEntry(data_count, 0));
		 * count_regdate= db.datacount("8"); yVals1.add(new BarEntry(data_count,
		 * 0)); count_regdate= db.datacount("9"); yVals1.add(new
		 * BarEntry(data_count, 0)); count_regdate= db.datacount("10");
		 * yVals1.add(new BarEntry(data_count, 0)); count_regdate=
		 * db.datacount("11"); yVals1.add(new BarEntry(data_count, 0));
		 */

		/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
		BarDataSet set1 = new BarDataSet(yVals1, "월별 독서량");
		set1.setValueTextSize(2);// 통계바 밑에 글씨 크기조절

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);
		data.setValueTextSize(10f);
		data.setValueTypeface(tf);

		mChart.setData(data);
	}

	@SuppressLint("NewApi")
	@Override
	public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

		if (e == null)
			return;

		RectF bounds = mChart.getBarBounds((BarEntry) e);
		PointF position = mChart.getPosition(e, mChart.getData()
				.getDataSetByIndex(dataSetIndex).getAxisDependency());

		Log.i("bounds", bounds.toString());
		Log.i("position", position.toString());
	}

	public void onNothingSelected() {
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	};
}
