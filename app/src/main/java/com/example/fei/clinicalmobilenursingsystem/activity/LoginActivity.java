package com.example.fei.clinicalmobilenursingsystem.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fei.clinicalmobilenursingsystem.R;
import com.example.fei.clinicalmobilenursingsystem.adapter.PopupWindowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VD on 2017/5/16.
 */
public class LoginActivity extends BaseActivity {

    private EditText editTextUsername;
    private RelativeLayout relativeLayoutPasswordImage;
    private EditText editTextPassword;
    private RelativeLayout relativeLayoutWorkTeam;
    private TextView textViewWorkTeam;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Transparent();
        initView();
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ints = {R.id.editText_username, R.id.editText_password};
        return ints;
    }

    @Override
    public View[] filterViewByIds() {
        View[] views = {editTextPassword, editTextUsername};
        return views;
    }


    /**
     * 插件自动生成的初始化控件方法
     */
    private void initView() {
        editTextUsername = (EditText) findViewById(R.id.editText_username);
        relativeLayoutPasswordImage = (RelativeLayout) findViewById(R.id.relativeLayout_password_image);
        editTextPassword = (EditText) findViewById(R.id.editText_password);
        relativeLayoutWorkTeam = (RelativeLayout) findViewById(R.id.relativeLayout_work_team);
        textViewWorkTeam = (TextView) findViewById(R.id.textView_work_team_show);
        buttonLogin = (Button) findViewById(R.id.button_login);
        relativeLayoutPasswordImage.setVisibility(View.GONE);
        setListener();
    }

    /**
     * 设置控件的监听事件
     */
    private void setListener() {

        editTextPassword.addTextChangedListener(textWatcher);

        relativeLayoutPasswordImage.setOnClickListener(listener);
        relativeLayoutWorkTeam.setOnClickListener(listener);
        buttonLogin.setOnClickListener(listener);
    }

    /**
     * 控件的点击监听事件
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.relativeLayout_work_team:
                    showPopupWindow();
                    break;
                case R.id.relativeLayout_password_image:
                    editTextPassword.setText(null);
                    relativeLayoutPasswordImage.setVisibility(View.INVISIBLE);
                    break;
                case R.id.button_login:
                    if (checkLogin()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 获取工作组字符串集合
     *
     * @return 返回一个list集合，包含所有工作组名称字符串
     */
    private List<String> getWorkTeam() {
        List<String> list = new ArrayList<>();
        list.add("护理组1");
        list.add("护理组2");
        list.add("护理组3");
        list.add("护理组4");
        list.add("护理组5");
        list.add("护理组6");
        list.add("护理组7");
        list.add("护理组8");
        list.add("护理组5");
        list.add("护理组5");
        list.add("护理组5");

        return list;
    }

    /**
     * 将工作组选择框作为一个popupWindow形式展示
     */
    private void showPopupWindow() {
        final List<String> strings = getWorkTeam();
//		Log.e("VD", strings.size() + "");
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_login, null);
//        第一个参数是显示的视图，第二个参数是宽，第三个是高，第四个是否有焦点
        final PopupWindow popupWindow = new PopupWindow(view, 500, 500, true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        //popupWindow.setBackgroundDrawable(new BitmapDrawable())由于过时了，用下面代码设置透明背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ListView listView = (ListView) view.findViewById(R.id.lv_popupwindow);
        listView.setAdapter(new PopupWindowAdapter(strings, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textViewWorkTeam.setText(strings.get(position));
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(textViewWorkTeam);
    }

    /**
     * editText输入框的输入监听
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            relativeLayoutPasswordImage.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * 判断用户是否登录成功
     *
     * @return boolean类型  true 成功，false 失败
     */
    private boolean checkLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String workTeam = textViewWorkTeam.getText().toString().trim();
//		Log.e("VD", username.isEmpty() + "     " + username);
//		Log.e("VD", password.isEmpty() + "     " + password);
//		Log.e("VD", workTeam.isEmpty() + "     " + workTeam);
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(workTeam)) {
            Toast.makeText(this, "请选择工作组", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
