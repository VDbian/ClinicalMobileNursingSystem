package com.example.fei.clinicalmobilenursingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.fei.clinicalmobilenursingsystem.R;

import java.util.List;

/**
 * Created by VD on 2017/5/16.
 */

public class PopupWindowAdapter extends BaseAdapter {
	private List<String> strings;
	private Context context;
	private LayoutInflater inflater;


	public PopupWindowAdapter(List<String> strings, Context context) {
		this.strings = strings;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return strings == null ? 0 : strings.size();
	}

	@Override
	public Object getItem(int position) {
		return strings == null ? null : strings.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_popupwindow, null);
			viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_popupwindow);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String string = strings.get(position);
		viewHolder.textView.setText(string);
		return convertView;
	}

	class ViewHolder {
		private TextView textView;
	}
}