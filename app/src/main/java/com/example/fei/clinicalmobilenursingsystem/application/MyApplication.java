package com.example.fei.clinicalmobilenursingsystem.application;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by VD on 2017/6/20.
 */

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
//		this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//			@Override
//			public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//				Log.e("VD","created");
//			}
//
//			@Override
//			public void onActivityStarted(Activity activity) {
//
//			}
//
//			@Override
//			public void onActivityResumed(Activity activity) {
//
//			}
//
//			@Override
//			public void onActivityPaused(Activity activity) {
//
//			}
//
//			@Override
//			public void onActivityStopped(Activity activity) {
//
//			}
//
//			@Override
//			public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//			}
//
//			@Override
//			public void onActivityDestroyed(Activity activity) {
//				Log.e("VD","destroy");
//
//			}
//		});
	}
}
