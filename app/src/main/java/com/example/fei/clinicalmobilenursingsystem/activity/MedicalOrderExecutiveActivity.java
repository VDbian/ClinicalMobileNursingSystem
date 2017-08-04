package com.example.fei.clinicalmobilenursingsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fei.clinicalmobilenursingsystem.R;
import com.example.fei.clinicalmobilenursingsystem.adapter.ListViewMedicalOrdersAdapter;
import com.example.fei.clinicalmobilenursingsystem.bean.MedicalOrdersBean;

import java.util.ArrayList;
import java.util.List;

import qrcode.CaptureActivity;

/**
 * Created by VD on 2017/6/5.
 */

public class MedicalOrderExecutiveActivity extends BaseActivity {

	private RelativeLayout relativeLayoutBack;
	private RelativeLayout relativeLayoutScan;
	private EditText editTextStrapIdentify;
	private EditText editTextBedsideCard;
	private LinearLayout   linearLayoutInformation;
	private TextView textViewPatientNameShow;
	private TextView textViewPatientSexShow;
	private TextView textViewPatientAgeShow;
	private TextView textViewPatientBedNumShow;
	private TextView textViewError;
	private ListView listViewMedicalOrderExecutive;
	private Button buttonExecutive;
	private Button buttonExecutiveAll;


	private List<MedicalOrdersBean> medicalOrdersBeanList = new ArrayList<>();
	private ListViewMedicalOrdersAdapter listViewMedicalOrdersAdapter;

	private int focus = 0;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_orders_executive);
		Transparent();//设置透明状态栏
		initView();
	}

	/**
	 * 返回一组需要实现隐藏editText控件的ID值
	 * @return 一组 int[]值
	 */
	@Override
	public int[] hideSoftByEditViewIds() {
		int[] ints = {R.id.editText_bedside_card, R.id.editText_strap_identify};
		return ints;
	}

	@Override
	public View[] filterViewByIds() {
		View[] views = {editTextStrapIdentify,editTextBedsideCard};
		return views;
	}

	/**
	 * 初始化控件，并调用设置监听方法
	 */
	private void initView() {
		relativeLayoutBack = (RelativeLayout) findViewById(R.id.relativeLayout_back);
		relativeLayoutScan = (RelativeLayout) findViewById(R.id.relativeLayout_scan);
		editTextStrapIdentify = (EditText) findViewById(R.id.editText_strap_identify);
		editTextBedsideCard = (EditText) findViewById(R.id.editText_bedside_card);
		textViewPatientNameShow = (TextView) findViewById(R.id.textView_patient_name_show);
		textViewPatientSexShow = (TextView) findViewById(R.id.textView_patient_sex_show);
		textViewPatientAgeShow = (TextView) findViewById(R.id.textView_patient_age_show);
		textViewPatientBedNumShow = (TextView) findViewById(R.id.textView_patient_bed_num_show);
		listViewMedicalOrderExecutive = (ListView) findViewById(R.id.listView_medical_order_executive);
		buttonExecutive = (Button) findViewById(R.id.button_executive);
		buttonExecutiveAll = (Button) findViewById(R.id.button_executive_all);
		linearLayoutInformation = (LinearLayout) findViewById(R.id.linearLayout_information);
		textViewError = (TextView) findViewById(R.id.textViewError);

		setListener();
	}

	/**
	 * 设置控件的事件监听
	 */
	private void setListener() {
		relativeLayoutBack.setOnClickListener(onClickListener);
		relativeLayoutScan.setOnClickListener(onClickListener);
		buttonExecutive.setOnClickListener(onClickListener);
		buttonExecutiveAll.setOnClickListener(onClickListener);

		editTextStrapIdentify.setOnEditorActionListener(onEditorActionListener);
		editTextBedsideCard.setOnEditorActionListener(onEditorActionListener);
		editTextStrapIdentify.setOnFocusChangeListener(onFocusChangeListener);
		editTextBedsideCard.setOnFocusChangeListener(onFocusChangeListener);

		getMedicalOrdersBeanList();
		listViewMedicalOrderExecutive.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_listview_medical_orders, null));
		listViewMedicalOrdersAdapter = new ListViewMedicalOrdersAdapter(medicalOrdersBeanList, this);
		listViewMedicalOrderExecutive.setAdapter(listViewMedicalOrdersAdapter);
		listViewMedicalOrderExecutive.setOnItemClickListener(onItemClickListener);
	}

	/**
	 * OnClickListener 点击事件监听
	 */
	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.relativeLayout_back:
					finish();
					break;
				case R.id.relativeLayout_scan:
					Intent intent = new Intent(MedicalOrderExecutiveActivity.this, CaptureActivity.class);
//					Log.e("VD", focus + "");
					if (focus == 0) {
						startActivityForResult(intent, 1001);
					}
					if (focus == 1) {
						startActivityForResult(intent, 1002);
					}
					break;
				case R.id.button_executive:

					break;
				case R.id.button_executive_all:

					break;
				default:
					break;
			}
		}
	};

	/**
	 *OnEditorActionListener  editText控件的点击软键盘完成键完成输入，隐藏软键盘
	 */
	TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			boolean isOk = true;
			switch (v.getId()) {
				case R.id.editText_strap_identify:
					if (actionId == EditorInfo.IME_ACTION_DONE) {
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(editTextStrapIdentify.getWindowToken(), 0);
						isDifferent();
					}
					break;
				case R.id.editText_bedside_card:
					if (actionId == EditorInfo.IME_ACTION_DONE) {
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(editTextBedsideCard.getWindowToken(), 0);
						isDifferent();
					}
					break;
				default:
					isOk = false;
					break;
			}
			return isOk;
		}
	};

	/**
	 * OnFocusChangeListener   监听View的焦点监听
	 */
	View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			switch (v.getId()) {
				case R.id.editText_bedside_card:
					if (hasFocus) {
						focus = 0;
					}
					break;
				case R.id.editText_strap_identify:
					if (hasFocus) {
						focus = 1;
					}
					break;
				default:
					break;
			}
		}
	};

	/**
	 * OnItemClickListener  listView的item点击事件监听
	 */
	private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		}
	};

	/**
	 * 获取医嘱信息
	 */
	private void getMedicalOrdersBeanList() {
		MedicalOrdersBean medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "1");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "2");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "3");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "4");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "5");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "6");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "7");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "8");
		medicalOrdersBeanList.add(medicalOrdersBean);
		medicalOrdersBean = new MedicalOrdersBean("普通", "口腔清洗", "", "次", "9");
		medicalOrdersBeanList.add(medicalOrdersBean);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == 1001) {
			editTextBedsideCard.setText(data.getStringExtra(CaptureActivity.EXTRA_RESULT));
			isDifferent();
		}
		if (resultCode == RESULT_OK && requestCode == 1002) {
			editTextStrapIdentify.setText(data.getStringExtra(CaptureActivity.EXTRA_RESULT));
			isDifferent();
		}
	}


	/**
	 * 判断床头卡和腕带是否是同一患者
	 */
	private void isDifferent(){
		boolean bool = true;
		String bedSideCard = editTextBedsideCard.getText().toString().trim();
		String strapIdentity = editTextStrapIdentify.getText().toString().trim();
		if (!TextUtils.isEmpty(bedSideCard)&&!TextUtils.isEmpty(strapIdentity)){
			if (!bedSideCard.equals(strapIdentity)){
				bool = false;
			}
		}
		if (bool){
			linearLayoutInformation.setVisibility(View.VISIBLE);
			textViewError.setVisibility(View.INVISIBLE);
		}else {
			linearLayoutInformation.setVisibility(View.INVISIBLE);
			textViewError.setVisibility(View.VISIBLE);
		}
	}
}
