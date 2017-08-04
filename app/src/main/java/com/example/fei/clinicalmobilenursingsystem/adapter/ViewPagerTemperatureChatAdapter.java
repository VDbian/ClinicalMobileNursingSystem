package com.example.fei.clinicalmobilenursingsystem.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.example.fei.clinicalmobilenursingsystem.view.ZoomImageView;

import java.util.List;

/**
 * Created by fei on 2017/4/25.
 */

public class ViewPagerTemperatureChatAdapter extends PagerAdapter {

	private List<ZoomImageView> zoomImageViewList;

	public ViewPagerTemperatureChatAdapter(List<ZoomImageView> zoomImageViewList) {
		this.zoomImageViewList = zoomImageViewList;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(zoomImageViewList.get(position));
		return zoomImageViewList.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(zoomImageViewList.get(position));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getCount() {
		return zoomImageViewList == null ? 0 : zoomImageViewList.size();
	}
}


//    @Override
//    public int getCount() {
//        return imageViewList == null ? 0 : imageViewList.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    //    创建Item
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(imageViewList.get(position));
//        return imageViewList.get(position);
//    }
//
//    //    删除Item
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(imageViewList.get(position));
//    }
//}
