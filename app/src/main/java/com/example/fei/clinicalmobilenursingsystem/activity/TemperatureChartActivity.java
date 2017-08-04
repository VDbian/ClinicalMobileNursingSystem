package com.example.fei.clinicalmobilenursingsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.fei.clinicalmobilenursingsystem.R;
import com.example.fei.clinicalmobilenursingsystem.adapter.ViewPagerTemperatureChatAdapter;
import com.example.fei.clinicalmobilenursingsystem.view.ZoomImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VD on 2017/6/23.
 */

public class TemperatureChartActivity extends BaseActivity {

	private RelativeLayout relativeLayoutBack;
	private ViewPager viewPagerTemperatureChart;

	private ViewPagerTemperatureChatAdapter viewPagerTemperatureChatAdapter;
	private List<ZoomImageView> zoomImageViewList;
	private List<View> pointViewList = new ArrayList<>();
	private LinearLayout linearLayoutPointContainer;
	private int firstIndex = 0;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperature_chart);
		Transparent();//设置透明状态栏
		initView();
	}


	private void initView() {
		relativeLayoutBack = (RelativeLayout) findViewById(R.id.relativeLayout_back);
		viewPagerTemperatureChart = (ViewPager) findViewById(R.id.viewPager_temperature_chart);
		linearLayoutPointContainer = (LinearLayout) findViewById(R.id.point_container);
		getResData();
		setListener();
		addPoint();
	}


	private void setListener() {
		relativeLayoutBack.setOnClickListener(onClickListener);
		viewPagerTemperatureChatAdapter = new ViewPagerTemperatureChatAdapter(zoomImageViewList);
		viewPagerTemperatureChart.setAdapter(viewPagerTemperatureChatAdapter);
		viewPagerTemperatureChart.addOnPageChangeListener(onPageChangeListener);
	}

	View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.relativeLayout_back:
					finish();
					break;
			}
		}
	};


	/*viewpager的滑动事件*/
	private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			changePointColor(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};

	private List<ZoomImageView> getResData() {
		zoomImageViewList = new ArrayList<>();
		zoomImageViewList.add(getImageView(R.mipmap.ic_launcher));
		zoomImageViewList.add(getImageView(R.mipmap.temperature));
		zoomImageViewList.add(getImageView(R.mipmap.ic_launcher));

		return zoomImageViewList;
	}

	/**
	 * 动态创建ImageView
	 *
	 * @param resId
	 * @return
	 */
	private ZoomImageView getImageView(int resId) {
		ZoomImageView zoomImageView = new ZoomImageView(this);
		zoomImageView.setImageResource(resId);
//		zoomImageView.setScaleType(ImageView.ScaleType.CENTER);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(300, 300);
		zoomImageView.setLayoutParams(layoutParams);

//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(resId);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(300, 300);
//        imageView.setLayoutParams(layoutParams);
		return zoomImageView;
	}


	/**
	 * 添加点组
	 */
	private void addPoint() {
		//dip 转换成px
		float scale = getResources().getDisplayMetrics().density;
		int size = (int) (10 * scale + 0.5f);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
		params.rightMargin = size;
		params.gravity = Gravity.CENTER_VERTICAL;
		for (int i = 0; i < zoomImageViewList.size(); i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_unchoose);
			pointViewList.add(point);
			linearLayoutPointContainer.addView(point, params);
		}
		//设置初始位置
		if (firstIndex != -1) {
			pointViewList.get(firstIndex).setBackgroundResource(R.drawable.shape_point_choose);
		}
	}

	/*改变小圆点的选中未选中状态*/
	private void changePointColor(int index) {
		for (int i = 0; i < pointViewList.size(); i++) {
			if (i == index) {
				pointViewList.get(index).setBackgroundResource(R.drawable.shape_point_choose);
			} else {
				pointViewList.get(i).setBackgroundResource(R.drawable.shape_point_unchoose);
			}
		}
	}

}
