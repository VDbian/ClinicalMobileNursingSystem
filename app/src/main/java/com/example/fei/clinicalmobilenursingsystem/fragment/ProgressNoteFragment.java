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

public class ProgressNoteFragment extends Fragment {
	private View view;
	private Button buttonFirst;
	private Button buttonPrevious;
	private Button buttonNext;
	private Button buttonLast;
	private TextView textViewRecordAdministrativeOfficeShow;
	private TextView textViewRecordPersonShow;
	private TextView textViewRecordTimeShow;
	private TextView textViewProgressNoteDetailShow;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_progress_note, null);
		initView();
		return view;
	}


	private void initView() {
		buttonFirst = (Button) view.findViewById(R.id.button_first);
		buttonPrevious = (Button) view.findViewById(R.id.button_previous);
		buttonNext = (Button) view.findViewById(R.id.button_next);
		buttonLast = (Button) view.findViewById(R.id.button_last);
		textViewRecordAdministrativeOfficeShow = (TextView) view.findViewById(R.id.textView_record_administrative_office_show);
		textViewRecordPersonShow = (TextView) view.findViewById(R.id.textView_record_person_show);
		textViewRecordTimeShow = (TextView) view.findViewById(R.id.textView_record_time_show);
		textViewProgressNoteDetailShow = (TextView) view.findViewById(R.id.textView_progress_note_detail_show);

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
