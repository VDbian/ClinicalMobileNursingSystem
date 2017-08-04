package com.example.fei.clinicalmobilenursingsystem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by fly on 2017-06-19.
 */

public class ViewPagerInformationSearchAdapter extends FragmentPagerAdapter {


	private List<Fragment> mList;

	public ViewPagerInformationSearchAdapter(FragmentManager fm, List<Fragment> mList) {
		super(fm);
		this.mList = mList;
	}

	@Override
	public Fragment getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}
}
