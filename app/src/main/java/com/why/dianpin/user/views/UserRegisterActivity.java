package com.why.dianpin.user.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.why.dianpin.R;
import com.why.dianpin.user.bean.UserBean;
import com.why.dianpin.util.HttpUtil;
import com.why.dianpin.util.PreferenceUtil;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * @author shidefeng
 * @since 2018/5/6.
 */

public class UserRegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordConf;
    private EditText mAge;
    private RadioButton mBtnMale;
    private RadioButton mBtnFeMale;
    private Button mBtnRegister;
    private View mLayoutMale;
    private View mLayoutFeMale;

    private int mSex = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_register);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        mUsername = UIUtils.findView(this, R.id.reg_username);
        mPassword = UIUtils.findView(this, R.id.reg_password);
        mPasswordConf = UIUtils.findView(this, R.id.reg_password_confirm);
        mAge = UIUtils.findView(this, R.id.reg_age);
        mLayoutMale = UIUtils.findView(this, R.id.reg_layout_male);
        mLayoutFeMale = UIUtils.findView(this, R.id.reg_layout_female);
        mBtnMale = UIUtils.findView(this, R.id.reg_male_btn);
        mBtnFeMale = UIUtils.findView(this, R.id.reg_female_btn);
        mBtnRegister = UIUtils.findView(this, R.id.reg_confirm);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("注册");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mLayoutMale.setOnClickListener(this);
        mLayoutFeMale.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

        RegTextWatcher watcher = new RegTextWatcher(this);
        mUsername.addTextChangedListener(watcher);
        mPassword.addTextChangedListener(watcher);
        mPasswordConf.addTextChangedListener(watcher);

        mBtnMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSex = 0;
                }
                updateBtn();
            }
        });
        mBtnFeMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSex = 1;
                }
                updateBtn();
            }
        });
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_layout_male:
                mBtnMale.setChecked(true);
                mBtnFeMale.setChecked(false);
                mSex = 0;
                break;

            case R.id.reg_layout_female:
                mBtnMale.setChecked(false);
                mBtnFeMale.setChecked(true);
                mSex = 1;
                break;

            case R.id.reg_confirm:
                if (checkPassword()) {
                    register(getUserBean());
                }
                break;
        }
    }

    private UserBean getUserBean() {
        UserBean user = new UserBean();
        user.username = mUsername.getText().toString();
        user.password = mPassword.getText().toString();
        String age = mAge.getText().toString();
        user.age = TextUtils.isEmpty(age) ? 0 : Integer.parseInt(age);
        user.sex = mSex;
        return user;
    }

    private void updateBtn() {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String confirm = mPasswordConf.getText().toString();

        if (TextUtils.isEmpty(username)) {
            mBtnRegister.setEnabled(false);
            return;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) {
            mBtnRegister.setEnabled(false);
            return;
        }
        if (mSex == -1) {
            mBtnRegister.setEnabled(false);
            return;
        }
        mBtnRegister.setEnabled(true);
    }

    private boolean checkPassword() {
        String password = mPassword.getText().toString();
        String confirm = mPasswordConf.getText().toString();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) {
            Toaster.show("密码为空");
            return false;
        }
        if (!TextUtils.equals(password, confirm)) {
            Toaster.show("密码不一致");
            return false;
        } else {
            Toaster.show("注册成功");
            return true;
        }
    }


    private void register(final UserBean user) {
        HttpUtil.create("user/addUser")
                .addParameter("username", user.username)
                .addParameter("password", user.password)
                .addParameter("age", user.age)
                .addParameter("sex", user.sex)
                .get(new HttpUtil.HttpCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        int successCode = result.optInt("resultCode", 0);
                        if (successCode == 1) {
                            PreferenceUtil.setValue(PreferenceUtil.KEY_USERNAME, user.username);
                            Toaster.show("注册成功");
                            finish();
                        } else {
                            Toaster.show("注册失败");
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Toaster.show(TextUtils.isEmpty(message) ? "注册失败" : message);
                    }
                });
    }

    private static class RegTextWatcher implements TextWatcher {
        private WeakReference<UserRegisterActivity> mFragment;

        public RegTextWatcher(UserRegisterActivity step2Fragment) {
            mFragment = new WeakReference<>(step2Fragment);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            UserRegisterActivity step2 = mFragment.get();
            if (step2 == null) return;
            step2.updateBtn();
        }
    }
}
