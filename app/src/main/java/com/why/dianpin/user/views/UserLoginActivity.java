package com.why.dianpin.user.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.why.dianpin.R;
import com.why.dianpin.util.HttpUtil;
import com.why.dianpin.util.PreferenceUtil;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONObject;

/**
 * @author xiaoyueyue
 * @since 2018/5/6.
 */

public class UserLoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private Button mBtnLogin;
    private Button mBtnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_login);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        mUsername = UIUtils.findView(this, R.id.et_username);
        mPassword = UIUtils.findView(this, R.id.et_password);
        mBtnLogin = UIUtils.findView(this, R.id.btn_login);
        mBtnRegister = UIUtils.findView(this, R.id.btn_register);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("登录");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_register:
                handleRegister();
                break;

            case R.id.btn_login:
                handleLogin();
                break;
        }
    }

    private void handleRegister() {
        startActivity(new Intent(this, UserLoginActivity.class));
        finish();
    }

    private void handleLogin() {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toaster.show("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toaster.show("密码不能为空");
            return;
        }

        login(username, password);
    }

    private void login(final String username, String password) {
        HttpUtil.create("user/userLogin")
                .addParameter("username", username)
                .addParameter("password", password)
                .get(new HttpUtil.HttpCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        int successCode = result.optInt("successCode", 0);
                        String failedMessage = result.optString("message", "");
                        if (successCode == 1) {
                            Toaster.show("登录成功");

                            onLoginSuccess(username);
                        } else {
                            Toaster.show(failedMessage);
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Toaster.show(TextUtils.isEmpty(message) ? "登录失败" : message);
                    }
                });
    }


    private void onLoginSuccess(String username) {
        PreferenceUtil.setValue(PreferenceUtil.KEY_USERNAME, username);

        setResult(RESULT_OK);
        finish();
    }
}
