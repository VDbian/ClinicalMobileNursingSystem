package com.example.fei.clinicalmobilenursingsystem.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fei.clinicalmobilenursingsystem.R;
import com.example.fei.clinicalmobilenursingsystem.adapter.ViewPagerInformationSearchAdapter;
import com.example.fei.clinicalmobilenursingsystem.fragment.CostBreakdownFragment;
import com.example.fei.clinicalmobilenursingsystem.fragment.NursingRecordFragment;
import com.example.fei.clinicalmobilenursingsystem.fragment.ProgressNoteFragment;

import java.util.ArrayList;
import java.util.List;

import qrcode.CaptureActivity;

/**
 * Created by VD on 2017/6/20.
 */

public class InformationSearchActivity extends BaseActivity {

	private RelativeLayout relativeLayoutBack;
	private RelativeLayout relativeLayoutScan;
	private EditText editTextBedsideCard;
	private TextView textViewPatientNameShow;
	private TextView textViewPatientSexShow;
	private TextView textViewPatientAgeShow;
	private TextView textViewPatientBedNumShow;
	private TextView textViewNursingRecord;
	private TextView textViewProgressNote;
	private TextView textViewCostBreakdown;
	private LinearLayout linearLayoutTabIndicator;
	private View tabIndicator;
	private ViewPager viewPagerInformationSearch;

	private ViewPagerInformationSearchAdapter viewPagerAdapter;
	private int screenWidth = 0;
	private int designWidth = 0;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information_search);
		Transparent();//设置透明状态栏
		initView();
	}

	@Override
	public View[] filterViewByIds() {
		View[] views = {editTextBedsideCard};
		return views;
	}

	@Override
	public int[] hideSoftByEditViewIds() {
		int[] ints = {R.id.editText_bedside_card};
		return ints;
	}

	private void initView() {
		relativeLayoutBack = (RelativeLayout) findViewById(R.id.relativeLayout_back);
		relativeLayoutScan = (RelativeLayout) findViewById(R.id.relativeLayout_scan);
		editTextBedsideCard = (EditText) findViewById(R.id.editText_bedside_card);
		textViewPatientNameShow = (TextView) findViewById(R.id.textView_patient_name_show);
		textViewPatientSexShow = (TextView) findViewById(R.id.textView_patient_sex_show);
		textViewPatientAgeShow = (TextView) findViewById(R.id.textView_patient_age_show);
		textViewPatientBedNumShow = (TextView) findViewById(R.id.textView_patient_bed_num_show);
		textViewNursingRecord = (TextView) findViewById(R.id.textView_nursing_record);
		textViewProgressNote = (TextView) findViewById(R.id.textView_progress_note);
		textViewCostBreakdown = (TextView) findViewById(R.id.textView_cost_breakdown);
		linearLayoutTabIndicator = (LinearLayout) findViewById(R.id.linearLayout_tab_indicator);
		tabIndicator = findViewById(R.id.tab_indicator);
		viewPagerInformationSearch = (ViewPager) findViewById(R.id.viewPager_information_search);

		getWidths();
		setListener();
	}

	private void setListener() {
		relativeLayoutBack.setOnClickListener(onClickListener);
		relativeLayoutScan.setOnClickListener(onClickListener);
		textViewCostBreakdown.setOnClickListener(onClickListener);
		textViewNursingRecord.setOnClickListener(onClickListener);
		textViewProgressNote.setOnClickListener(onClickListener);

		viewPagerAdapter = new ViewPagerInformationSearchAdapter(getSupportFragmentManager(), getFragments());
		viewPagerInformationSearch.setAdapter(viewPagerAdapter);
		viewPagerInformationSearch.addOnPageChangeListener(onPageChangeListener);
	}

	View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.textView_nursing_record:
					viewPagerInformationSearch.setCurrentItem(0);
					break;
				case R.id.textView_progress_note:
					viewPagerInformationSearch.setCurrentItem(1);
					break;
				case R.id.textView_cost_breakdown:
					viewPagerInformationSearch.setCurrentItem(2);
					break;
				case R.id.relativeLayout_back:
					finish();
					break;
				case R.id.relativeLayout_scan:
					startActivityForResult(new Intent(InformationSearchActivity.this, CaptureActivity.class), 1001);
					break;
				default:
					break;
			}
		}
	};


	ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//              获取tabIndicatorView的LayoutParams（长宽和位置信息）
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
			layoutParams.width = textViewNursingRecord.getWidth();
//                positionOffset偏移百分比
			if (positionOffset != 0 && positionOffset != 1) {

				layoutParams.leftMargin = (int) ((textViewNursingRecord.getWidth() + 88.0 / designWidth * screenWidth) * (position + positionOffset));
				if (position == 1) {
					layoutParams.width = (int) ((textViewProgressNote.getWidth() - textViewNursingRecord.getWidth()) * position * positionOffset) + textViewNursingRecord.getWidth();
				}
				tabIndicator.setLayoutParams(layoutParams);
			} else {
				layoutParams.leftMargin = (int) (textViewNursingRecord.getWidth() + 88.0 / designWidth * screenWidth) * position;
				if (position == 2) {
					layoutParams.width = textViewCostBreakdown.getWidth();
				}
				tabIndicator.setLayoutParams(layoutParams);
			}
//			setColorText(position);
			setColorText(position, positionOffset);
		}

		@Override
		public void onPageSelected(int position) {

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};

	/**
	 * 设置viewpager滑动时TextView的字体颜色渐变
	 * @param position 当前选中位置
	 * @param positionOffset 滑动偏移百分比
	 */
	private void setColorText(int position, float positionOffset) {
		int rNormal = 53;
		int gNormal = 53;
		int bNormal = 53;
		int rChecked = 9;
		int gChecked = 187;
		int bChecked = 7;

		int rNormalToChecked = (int) ((rChecked - rNormal) * positionOffset) + rNormal;
		int gNormalToChecked = (int) ((gChecked - gNormal) * positionOffset) + gNormal;
		int bNormalToChecked = (int) ((bChecked - bNormal) * positionOffset) + bNormal;

		int rCheckedToNormal = (int) ((rNormal - rChecked) * positionOffset) + rChecked;
		int gCheckedToNormal = (int) ((gNormal - gChecked) * positionOffset) + gChecked;
		int bCheckedToNormal = (int) ((bNormal - bChecked) * positionOffset) + bChecked;
		if (positionOffset != 0 && positionOffset != 1) {
			if (position == 0) {
				textViewNursingRecord.setTextColor(Color.rgb(rCheckedToNormal, gCheckedToNormal, bCheckedToNormal));
				textViewProgressNote.setTextColor(Color.rgb(rNormalToChecked, gNormalToChecked, bNormalToChecked));
			}
			if (position == 1) {
				textViewProgressNote.setTextColor(Color.rgb(rCheckedToNormal, gCheckedToNormal, bCheckedToNormal));
				textViewCostBreakdown.setTextColor(Color.rgb(rNormalToChecked, gNormalToChecked, bNormalToChecked));
			}
		} else {
			textViewCostBreakdown.setTextColor(Color.rgb(rNormal, gNormal, bNormal));
			textViewNursingRecord.setTextColor(Color.rgb(rNormal, gNormal, bNormal));
			textViewProgressNote.setTextColor(Color.rgb(rNormal, gNormal, bNormal));
			switch (position) {
				case 0:
					textViewNursingRecord.setTextColor(Color.rgb(rChecked, gChecked, bChecked));
					break;
				case 1:
					textViewProgressNote.setTextColor(Color.rgb(rChecked, gChecked, bChecked));
					break;
				case 2:
					textViewCostBreakdown.setTextColor(Color.rgb(rChecked, gChecked, bChecked));
					break;
				default:
					break;
			}
		}
	}


	private List<Fragment> getFragments() {
		List<Fragment> mList = new ArrayList<>();
		mList.add(new NursingRecordFragment());
		mList.add(new ProgressNoteFragment());
		mList.add(new CostBreakdownFragment());
		return mList;
	}

	private void getWidths() {
		DisplayMetrics dm = new DisplayMetrics();
		//取得窗口属性
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//窗口的宽度
		screenWidth = dm.widthPixels;
		ApplicationInfo appInfo = null;
		try {
			appInfo = this.getPackageManager()
					.getApplicationInfo(getPackageName(),
							PackageManager.GET_META_DATA);
		} catch (PackageManager.NameNotFoundException e) {
//			Log.e("vd", e.getMessage());
			e.printStackTrace();
		}
		designWidth = appInfo.metaData.getInt("design_width");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1001 && resultCode == RESULT_OK) {
			editTextBedsideCard.setText(data.getStringExtra(CaptureActivity.EXTRA_RESULT));
		}
	}
}
