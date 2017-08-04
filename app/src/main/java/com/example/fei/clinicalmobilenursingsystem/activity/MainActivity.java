package com.example.fei.clinicalmobilenursingsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fei.clinicalmobilenursingsystem.R;
import com.example.fei.clinicalmobilenursingsystem.util.LogUtils;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by VD on 2017/5/23.
 */

public class MainActivity extends BaseActivity {

	private RoundedImageView roundedImageView;
	private TextView textUsername;
	private RelativeLayout relativeLayoutMedicalOrdersTreating;
	private RelativeLayout relativeLayoutMedicalOrdersExecutive;
	private RelativeLayout relativeLayoutCareToCollect;
	private RelativeLayout relativeLayoutInformationSearch;
	private Button buttonLogout;

	private long clickTime=0;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Transparent();//设置透明状态栏
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {

		roundedImageView = (RoundedImageView) findViewById(R.id.roundedImageView);
		textUsername = (TextView) findViewById(R.id.text_username);
		relativeLayoutMedicalOrdersTreating = (RelativeLayout) findViewById(R.id.relativeLayout_medical_orders_treating);
		relativeLayoutMedicalOrdersExecutive = (RelativeLayout) findViewById(R.id.relativeLayout_medical_orders_executive);
		relativeLayoutCareToCollect = (RelativeLayout) findViewById(R.id.relativeLayout_care_to_collect);
		relativeLayoutInformationSearch = (RelativeLayout) findViewById(R.id.relativeLayout_information_search);
		buttonLogout = (Button) findViewById(R.id.button_logout);

		setListener();
	}

	/**
	 * 设置控件的各自监听事件
	 */
	private void setListener() {

		relativeLayoutMedicalOrdersTreating.setOnClickListener(onClickListener);
		relativeLayoutMedicalOrdersExecutive.setOnClickListener(onClickListener);
		relativeLayoutCareToCollect.setOnClickListener(onClickListener);
		relativeLayoutInformationSearch.setOnClickListener(onClickListener);
		buttonLogout.setOnClickListener(onClickListener);
	}

	/**
	 * 可点击控件的点击监听事件
	 */
	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.relativeLayout_medical_orders_treating:
					startActivity(new Intent(MainActivity.this,MedicalOrderTreatingActivity.class));
					break;
				case R.id.relativeLayout_medical_orders_executive:
					startActivity(new Intent(MainActivity.this,MedicalOrderExecutiveActivity.class));
					break;
				case R.id.relativeLayout_care_to_collect:
					startActivity(new Intent(MainActivity.this,CareToCollectActivity.class));
					break;
				case R.id.relativeLayout_information_search:
					startActivity(new Intent(MainActivity.this,InformationSearchActivity.class));
					break;
				case R.id.button_logout:
					startActivity(new Intent(MainActivity.this, LoginActivity.class));
					finish();
					break;
				default:
					break;
			}
		}
	};

	/**
	 * 监听返回键，如果两次返回键按下的时间在一定时间内，退出程序
	 * @param keyCode
	 * @param event
	 * @return
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			LogUtils.e("BACK");
			if (SystemClock.uptimeMillis() - clickTime <= 1500) {
				//如果两次的时间差＜1s，就不执行操作
				finish();
				System.exit(0);
			} else {
				//当前系统时间的毫秒值
				clickTime = SystemClock.uptimeMillis();
				Toast.makeText(MainActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
