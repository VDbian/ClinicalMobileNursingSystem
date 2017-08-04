package com.example.fei.clinicalmobilenursingsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.example.fei.clinicalmobilenursingsystem.R;

/**
 * Created by VD on 2017/5/23.
 */

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//设置全屏，没有状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_splash);

		handler.sendEmptyMessageDelayed(1001,3000);
	}



	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 1001:
					startActivity(new Intent(SplashActivity.this, LoginActivity.class));
					finish();
					break;
				default:
					break;
			}
		}
	};
}
