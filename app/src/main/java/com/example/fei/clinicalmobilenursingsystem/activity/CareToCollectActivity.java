package com.example.fei.clinicalmobilenursingsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fei.clinicalmobilenursingsystem.R;

import qrcode.CaptureActivity;

/**
 * Created by VD on 2017/6/19.
 */

public class CareToCollectActivity extends BaseActivity {

	private RelativeLayout relativeLayoutBack;
	private RelativeLayout relativeLayoutScan;
	private EditText editTextBedsideCard;
	private TextView textViewPatientNameShow;
	private TextView textViewPatientSexShow;
	private TextView textViewPatientAgeShow;
	private TextView textViewPatientBedNumShow;
	private Button buttonTemperatureChart;
	private Button buttonImportTheLastRecord;
	private Button buttonSave;
	private EditText editTextTemperature;
	private EditText editTextPulse;
	private EditText editTextBreathe;
	private EditText editTextBloodGlucose;
	private EditText editTextIntroduceQuantity;
	private EditText editTextOutputQuantity;
	private EditText editTextSystolicPressure;
	private EditText editTextDiastolicPressure;
	private EditText editTextHeight;
	private EditText editTextWeight;
	private EditText editTextBowelFrequency;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_care_to_collect);
		Transparent();//设置透明状态栏
		initView();
	}

	@Override
	public View[] filterViewByIds() {
		View[] views = {editTextBedsideCard, editTextBloodGlucose, editTextBowelFrequency,
				editTextBreathe, editTextDiastolicPressure, editTextHeight,
				editTextIntroduceQuantity, editTextWeight, editTextSystolicPressure,
				editTextOutputQuantity, editTextPulse, editTextTemperature
		};
		return views;
	}

	@Override
	public int[] hideSoftByEditViewIds() {
		int[] ints = {R.id.editText_bedside_card, R.id.editText_temperature, R.id.editText_pulse,
				R.id.editText_breathe, R.id.editText_blood_glucose, R.id.editText_introduce_quantity,
				R.id.editText_output_quantity, R.id.editText_systolic_pressure, R.id.editText_diastolic_pressure,
				R.id.editText_height, R.id.editText_weight, R.id.editText_bowel_frequency
		};
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
		buttonTemperatureChart = (Button) findViewById(R.id.button_temperature_chart);
		buttonImportTheLastRecord = (Button) findViewById(R.id.button_import_the_last_record);
		buttonSave = (Button) findViewById(R.id.button_save);
		editTextTemperature = (EditText) findViewById(R.id.editText_temperature);
		editTextPulse = (EditText) findViewById(R.id.editText_pulse);
		editTextBreathe = (EditText) findViewById(R.id.editText_breathe);
		editTextBloodGlucose = (EditText) findViewById(R.id.editText_blood_glucose);
		editTextIntroduceQuantity = (EditText) findViewById(R.id.editText_introduce_quantity);
		editTextOutputQuantity = (EditText) findViewById(R.id.editText_output_quantity);
		editTextSystolicPressure = (EditText) findViewById(R.id.editText_systolic_pressure);
		editTextDiastolicPressure = (EditText) findViewById(R.id.editText_diastolic_pressure);
		editTextHeight = (EditText) findViewById(R.id.editText_height);
		editTextWeight = (EditText) findViewById(R.id.editText_weight);
		editTextBowelFrequency = (EditText) findViewById(R.id.editText_bowel_frequency);

		setListener();
	}

	private void setListener() {
		relativeLayoutBack.setOnClickListener(onClickListener);
		relativeLayoutScan.setOnClickListener(onClickListener);
		buttonImportTheLastRecord.setOnClickListener(onClickListener);
		buttonSave.setOnClickListener(onClickListener);
		buttonTemperatureChart.setOnClickListener(onClickListener);
	}

	View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.relativeLayout_back:
					finish();
					break;
				case R.id.relativeLayout_scan:
					startActivityForResult(new Intent(CareToCollectActivity.this, CaptureActivity.class), 1001);
					break;
				case R.id.button_temperature_chart:
					startActivity(new Intent(CareToCollectActivity.this,TemperatureChartActivity.class));
					break;
				case R.id.button_import_the_last_record:
					break;
				case R.id.button_save:
					break;
				default:
					break;
			}
		}
	};


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1001 && resultCode == RESULT_OK) {
			editTextBedsideCard.setText(data.getStringExtra(CaptureActivity.EXTRA_RESULT));
		}
	}
}
