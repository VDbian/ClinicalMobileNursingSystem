package com.example.fei.clinicalmobilenursingsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fei.clinicalmobilenursingsystem.R;

/**
 * Created by VD on 2017/6/20.
 */

public class NursingRecordFragment extends Fragment {
	private View view;
	private Button buttonFirst;
	private Button buttonPrevious;
	private Button buttonNext;
	private Button buttonLast;
	private TextView textViewTallyAdministrativeOfficeShow;
	private TextView textViewTallyPersonShow;
	private TextView textViewTallyTimeShow;
	private TextView textViewNurseTimeShow;
	private TextView textViewNurseLevelShow;
	private TextView textViewNurseDescribeShow;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_nursing_record, null);
		initView();
		return view;
	}

	private void initView() {
		buttonFirst = (Button) view.findViewById(R.id.button_first);
		buttonPrevious = (Button) view.findViewById(R.id.button_previous);
		buttonNext = (Button) view.findViewById(R.id.button_next);
		buttonLast = (Button) view.findViewById(R.id.button_last);
		textViewTallyAdministrativeOfficeShow = (TextView) view.findViewById(R.id.textView_tally_administrative_office_show);
		textViewTallyPersonShow = (TextView) view.findViewById(R.id.textView_tally_person_show);
		textViewTallyTimeShow = (TextView) view.findViewById(R.id.textView_tally_time_show);
		textViewNurseTimeShow = (TextView) view.findViewById(R.id.textView_nurse_time_show);
		textViewNurseLevelShow = (TextView) view.findViewById(R.id.textView_nurse_level_show);
		textViewNurseDescribeShow = (TextView) view.findViewById(R.id.textView_nurse_describe_show);

		setListener();
	}

	private void setListener() {
		buttonFirst.setOnClickListener(onClickListener);
		buttonPrevious.setOnClickListener(onClickListener);
		buttonNext.setOnClickListener(onClickListener);
		buttonLast.setOnClickListener(onClickListener);
	}

	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.button_first:
					break;
				case R.id.button_previous:
					break;
				case R.id.button_next:
					break;
				case R.id.button_last:
					break;
				default:
					break;

			}
		}
	};

}
