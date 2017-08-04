package com.example.fei.clinicalmobilenursingsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fei.clinicalmobilenursingsystem.R;
import com.example.fei.clinicalmobilenursingsystem.adapter.ListViewMedicalOrdersAdapter;
import com.example.fei.clinicalmobilenursingsystem.bean.MedicalOrdersBean;

import java.util.ArrayList;
import java.util.List;

import qrcode.CaptureActivity;

/**
 * Created by VD on 2017/6/2.
 */

public class MedicalOrderTreatingActivity extends BaseActivity {

	private RelativeLayout relativeLayoutBack;
	private RelativeLayout relativeLayoutScan;
	//	private RadioGroup radioGroupMedicalOrderTreating;
	private EditText editTextSearch;
	private ImageView imageViewSearch;
	private TextView textViewPatientNameShow;
	private TextView textViewPatientSexShow;
	private TextView textViewPatientAgeShow;
	private TextView textViewPatientBedNumShow;
	private Button buttonAffirm;
	private ListView listViewMedicalOrderTreating;
	//	private RadioButton radioButtonPending;
//	private RadioButton radioButtonAlreadyTransferCopy;
//	private RadioButton radioButtonAlreadyProofread;
//	private RadioButton radioButtonAlreadyDispensing;
//	private RadioButton radioButtonAlreadyExecutive;
	private View viewUnderline;
	private TextView textViewPending;
	private TextView textViewAlreadyTransferCopy;
	private TextView textViewAlreadyProofread;
	private TextView textViewAlreadyDispensing;
	private TextView textViewAlreadyExecutive;

	private ListViewMedicalOrdersAdapter listViewMedicalOrdersAdapter;

	private List<MedicalOrdersBean> medicalOrdersBeanList = new ArrayList();

	private int screenWidth;
	private int designWidth;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_order_treating);
		Transparent();//设置透明状态栏
		initView();
	}

	@Override
	public int[] hideSoftByEditViewIds() {
		int[] ints = {R.id.editText_search};
		return ints;
	}

	@Override
	public View[] filterViewByIds() {
		View[] views = {editTextSearch};
		return views;
	}

	private void initView() {
		relativeLayoutBack = (RelativeLayout) findViewById(R.id.relativeLayout_back);
		relativeLayoutScan = (RelativeLayout) findViewById(R.id.relativeLayout_scan);
//		radioGroupMedicalOrderTreating = (RadioGroup) findViewById(R.id.radioGroup_medical_order_treating);
		editTextSearch = (EditText) findViewById(R.id.editText_search);
		imageViewSearch = (ImageView) findViewById(R.id.imageView_search);
		textViewPatientNameShow = (TextView) findViewById(R.id.textView_patient_name_show);
		textViewPatientSexShow = (TextView) findViewById(R.id.textView_patient_sex_show);
		textViewPatientAgeShow = (TextView) findViewById(R.id.textView_patient_age_show);
		textViewPatientBedNumShow = (TextView) findViewById(R.id.textView_patient_bed_num_show);
		buttonAffirm = (Button) findViewById(R.id.button_affirm);
		listViewMedicalOrderTreating = (ListView) findViewById(R.id.listView_medical_order_treating);
//		radioButtonPending = (RadioButton) findViewById(R.id.radioButton_pending);
//		radioButtonAlreadyTransferCopy = (RadioButton) findViewById(R.id.radioButton_already_transfer_copy);
//		radioButtonAlreadyProofread = (RadioButton) findViewById(R.id.radioButton_already_proofread);
//		radioButtonAlreadyDispensing = (RadioButton) findViewById(R.id.radioButton_already_dispensing);
//		radioButtonAlreadyExecutive = (RadioButton) findViewById(R.id.radioButton_already_executive);
		viewUnderline = findViewById(R.id.view_underline);
		textViewAlreadyDispensing = (TextView) findViewById(R.id.textView_already_dispensing);
		textViewAlreadyExecutive = (TextView) findViewById(R.id.textView_already_executive);
		textViewAlreadyProofread = (TextView) findViewById(R.id.textView_already_proofread);
		textViewAlreadyTransferCopy = (TextView) findViewById(R.id.textView_already_transfer_copy);
		textViewPending = (TextView) findViewById(R.id.textView_pending);

		getWidths();
		getMedicalOrdersBeanList();
		setListener();
		setUnderline(5);

	}

	private void setListener() {
		listViewMedicalOrderTreating.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_listview_medical_orders, null));
		listViewMedicalOrdersAdapter = new ListViewMedicalOrdersAdapter(medicalOrdersBeanList, this);
		listViewMedicalOrderTreating.setAdapter(listViewMedicalOrdersAdapter);
		listViewMedicalOrderTreating.setOnItemClickListener(onItemClickListener);

//		radioGroupMedicalOrderTreating.setOnCheckedChangeListener(onCheckedChangeListener);

		editTextSearch.addTextChangedListener(textWatcher);

		textViewPending.setOnClickListener(onClickListener);
		textViewAlreadyTransferCopy.setOnClickListener(onClickListener);
		textViewAlreadyProofread.setOnClickListener(onClickListener);
		textViewAlreadyExecutive.setOnClickListener(onClickListener);
		textViewAlreadyDispensing.setOnClickListener(onClickListener);
		buttonAffirm.setOnClickListener(onClickListener);
		relativeLayoutBack.setOnClickListener(onClickListener);
		relativeLayoutScan.setOnClickListener(onClickListener);

		editTextSearch.setOnEditorActionListener(onEditorActionListener);
	}

	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.relativeLayout_back:
					finish();
					break;
				case R.id.relativeLayout_scan:
					Intent intent = new Intent(MedicalOrderTreatingActivity.this, CaptureActivity.class);
					startActivityForResult(intent, 1001);
					break;
				case R.id.button_affirm:
					switch (buttonAffirm.getText().toString()) {
						case "医嘱转抄":
							Toast.makeText(MedicalOrderTreatingActivity.this, "医嘱转抄", Toast.LENGTH_SHORT).show();
							break;
						case "医嘱校对":
							Toast.makeText(MedicalOrderTreatingActivity.this, "医嘱校对", Toast.LENGTH_SHORT).show();
							break;
						case "医嘱发药":
							Toast.makeText(MedicalOrderTreatingActivity.this, "医嘱发药", Toast.LENGTH_SHORT).show();
							break;
						case "医嘱执行":
							Toast.makeText(MedicalOrderTreatingActivity.this, "医嘱执行", Toast.LENGTH_SHORT).show();
							break;
						default:
							break;
					}
					break;
				case R.id.textView_pending:
					setUnderline(0);
					changeData(0);
					break;
				case R.id.textView_already_transfer_copy:
					setUnderline(1);
					changeData(1);
					break;
				case R.id.textView_already_proofread:
					setUnderline(2);
					changeData(2);
					break;
				case R.id.textView_already_dispensing:
					setUnderline(3);
					changeData(3);
					break;
				case R.id.textView_already_executive:
					setUnderline(4);
					changeData(4);
					break;
				default:
					break;
			}
		}
	};

	private TextWatcher textWatcher = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
//				imageViewSearch.setVisibility(View.INVISIBLE);
		}

		@Override
		public void afterTextChanged(Editable s) {
			if (s.length() == 0) {
				imageViewSearch.setVisibility(View.VISIBLE);
			} else {
				imageViewSearch.setVisibility(View.INVISIBLE);
			}
		}
	};

	TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			boolean isOk = true;
			switch (actionId) {
				case EditorInfo.IME_ACTION_DONE:
					//点击软键盘的enter键，收起软键盘完成输入
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
					Toast.makeText(MedicalOrderTreatingActivity.this, "完成输入", Toast.LENGTH_SHORT).show();
					break;
				default:
					isOk = false;
					break;
			}

			return isOk;
		}
	};


//	private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
//		@Override
//		public void onCheckedChanged(RadioGroup group, int checkedId) {
//			switch (checkedId){
//				case R.id.radioButton_already_transfer_copy:
//
//					break;
//				case R.id.radioButton_pending:
//
//					break;
//				case R.id.radioButton_already_proofread:
//
//					break;
//				case R.id.radioButton_already_dispensing:
//
//					break;
//				case R.id.radioButton_already_executive:
//
//					break;
//			}
//		}
//	};

	private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		}
	};

	private void getMedicalOrdersBeanList() {
//        medicalOrdersBeenList = new ArrayList<>();
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


	/**
	 * 设置下划线位置
	 * @param position textView的位置
	 */
	private void setUnderline(int position) {
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewUnderline.getLayoutParams();

//		Log.e("VD",screenWidth+"");
		switch (position) {
			case 0:
				layoutParams.width = textViewPending.getWidth();
				layoutParams.leftMargin = (int) (27.0 / designWidth * screenWidth+0.5f);
//				Log.e("VD",layoutParams.width+"******"+layoutParams.leftMargin );
				viewUnderline.setLayoutParams(layoutParams);
				setColorText();
				textViewPending.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupChecked));
				break;
			case 1:
				layoutParams.width = textViewAlreadyTransferCopy.getWidth();
				layoutParams.leftMargin = (int) ((27.0 + 54 * position) / designWidth * screenWidth+0.5f) + textViewPending.getWidth();
				viewUnderline.setLayoutParams(layoutParams);
				setColorText();
				textViewAlreadyTransferCopy.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupChecked));
				break;
			case 2:
				layoutParams.width = textViewAlreadyProofread.getWidth();
				layoutParams.leftMargin = (int) ((27.0 + 54 * position) / designWidth * screenWidth+0.5f)
						+ textViewPending.getWidth() + textViewAlreadyTransferCopy.getWidth();
				viewUnderline.setLayoutParams(layoutParams);
				setColorText();
				textViewAlreadyProofread.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupChecked));
				break;
			case 3:
				layoutParams.width = textViewAlreadyDispensing.getWidth();
				layoutParams.leftMargin = (int) ((27.0 + 54 * position) / designWidth * screenWidth+0.5f)
						+ textViewPending.getWidth() + textViewAlreadyTransferCopy.getWidth() +
						textViewAlreadyProofread.getWidth();
				viewUnderline.setLayoutParams(layoutParams);
				setColorText();
				textViewAlreadyDispensing.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupChecked));
				break;
			case 4:
				layoutParams.width = textViewAlreadyExecutive.getWidth();
				layoutParams.leftMargin = (int) ((27.0 + 54 * position) / designWidth * screenWidth+0.5f)
						+ textViewPending.getWidth() + textViewAlreadyTransferCopy.getWidth() +
						textViewAlreadyProofread.getWidth() + textViewAlreadyDispensing.getWidth();
				viewUnderline.setLayoutParams(layoutParams);
				setColorText();
				textViewAlreadyExecutive.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupChecked));
				break;
			case 5:
				layoutParams.width = (int) (96.0 / designWidth * screenWidth+0.5f);
				layoutParams.leftMargin = (int) (27.0 / designWidth * screenWidth+0.5f);
				viewUnderline.setLayoutParams(layoutParams);
//				Log.e("VD",layoutParams.width+"------"+layoutParams.leftMargin );
				setColorText();
				textViewPending.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupChecked));
				break;
			default:
				break;
		}
	}

	/**
	 * 重置textView的初始颜色
	 */
	private void setColorText() {
		textViewPending.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupNormal));
		textViewAlreadyProofread.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupNormal));
		textViewAlreadyDispensing.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupNormal));
		textViewAlreadyTransferCopy.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupNormal));
		textViewAlreadyExecutive.setTextColor(ContextCompat.getColor(this, R.color.colorRadioGroupNormal));
	}

	/**
	 *根据点击的textView控件，更改listView的数据源
	 * @param position textView的位置
	 */
	private void changeData(int position) {
		MedicalOrdersBean medicalOrdersBean;
		switch (position) {
			case 0:
				medicalOrdersBeanList.clear();
				medicalOrdersBean = new MedicalOrdersBean("普通0", "口腔清洗", "", "次", "1");
				medicalOrdersBeanList.add(medicalOrdersBean);
				listViewMedicalOrdersAdapter.notifyDataSetChanged();
				buttonAffirm.setText("医嘱转抄");
				buttonAffirm.setVisibility(View.VISIBLE);
				break;
			case 1:
				medicalOrdersBeanList.clear();
//				medicalOrdersBean = new MedicalOrdersBean("普通1", "口腔清洗", "", "次", "1");
//				medicalOrdersBeanList.add(medicalOrdersBean);
				listViewMedicalOrdersAdapter.notifyDataSetChanged();
				buttonAffirm.setText("医嘱校对");
				buttonAffirm.setVisibility(View.VISIBLE);
				break;
			case 2:
				medicalOrdersBeanList.clear();
				medicalOrdersBean = new MedicalOrdersBean("普通2", "口腔清洗", "", "次", "1");
				medicalOrdersBeanList.add(medicalOrdersBean);
				listViewMedicalOrdersAdapter.notifyDataSetChanged();
				buttonAffirm.setText("医嘱发药");
				buttonAffirm.setVisibility(View.VISIBLE);
				break;
			case 3:
				medicalOrdersBeanList.clear();
				medicalOrdersBean = new MedicalOrdersBean("普通3", "口腔清洗", "", "次", "1");
				medicalOrdersBeanList.add(medicalOrdersBean);
				listViewMedicalOrdersAdapter.notifyDataSetChanged();
				buttonAffirm.setText("医嘱执行");
				buttonAffirm.setVisibility(View.VISIBLE);
				break;
			case 4:
				medicalOrdersBeanList.clear();
				medicalOrdersBean = new MedicalOrdersBean("普通4", "口腔清洗", "", "次", "1");
				medicalOrdersBeanList.add(medicalOrdersBean);
				listViewMedicalOrdersAdapter.notifyDataSetChanged();
				buttonAffirm.setVisibility(View.INVISIBLE);
				break;
			default:
				break;
		}

	}


	private void getWidths(){
		DisplayMetrics dm = new DisplayMetrics();
		//取得窗口属性
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//窗口的宽度
		screenWidth = dm.widthPixels;
		ApplicationInfo appInfo = null;
		try {
			appInfo = this.getPackageManager()
					.getApplicationInfo(getPackageName(),
							PackageManager.GET_META_DATA);
		} catch (PackageManager.NameNotFoundException e) {
//			Log.e("vd", e.getMessage());
			e.printStackTrace();
		}
		designWidth= appInfo.metaData.getInt("design_width");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1001 && resultCode == RESULT_OK) {
			editTextSearch.setText(data.getStringExtra(CaptureActivity.EXTRA_RESULT));
		}
	}

}
