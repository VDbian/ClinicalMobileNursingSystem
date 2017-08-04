package com.example.fei.clinicalmobilenursingsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fei.clinicalmobilenursingsystem.R;

/**
 * Created by VD on 2017/6/20.
 */

public class CostBreakdownFragment extends Fragment {
	private View view;
	private TextView textViewTotalCostShow;
	private TextView textViewDepositAmountShow;
	private TextView textViewCostBreakdownShow;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_cost_breakdown, null);
		initView();
		return view;
	}

	private void initView() {
		textViewTotalCostShow = (TextView) view.findViewById(R.id.textView_total_cost_show);
		textViewDepositAmountShow = (TextView) view.findViewById(R.id.textView_deposit_amount_show);
		textViewCostBreakdownShow = (TextView) view.findViewById(R.id.textView_cost_breakdown_show);
	}


}
