package com.why.dianpin.question.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.question.adapter.QuestionDetailAdapter;
import com.why.dianpin.question.bean.AnswerBean;
import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.question.bean.QuestionDetailItemBean;
import com.why.dianpin.question.bean.QuestionDetailQuestionBean;
import com.why.dianpin.question.bean.QuestionDetailTipsBean;
import com.why.dianpin.scenic.bean.ScenicDetailHeaderBean;
import com.why.dianpin.scenic.bean.ScenicDetailItemBean;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.travel.bean.IDetailBean;
import com.why.dianpin.user.bean.UserBean;
import com.why.dianpin.util.HttpUtils;
import com.why.dianpin.util.PreferenceUtil;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class AddQuestionActivity extends BaseActivity {

    private EditText mContent;
    private TextView mBtnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_add_question);

        initViews();
        initEvent();
    }

    private void initViews() {
        mContent = UIUtils.findView(this, R.id.et_question);
        mBtnAdd = UIUtils.findView(this, R.id.btn_question);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("提问");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final AddTextWatcher watcher = new AddTextWatcher(this);
        mContent.addTextChangedListener(watcher);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });
    }

    private void addQuestion() {
        HashMap<String, String> params = new HashMap<>();
        params.put("question", mContent.getText().toString());
        params.put("timestamp", System.currentTimeMillis() + "");
        String userJson = PreferenceUtil.getValue(PreferenceUtil.KEY_USER, "");
        try {
            params.put("userId", UserBean.fromJson(new JSONObject(userJson)).id + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpUtils.doPost("question/addQuestion", params, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                int resultCode = result.optInt("resultCode");
                if (resultCode == 1) {
                    Toaster.show("提问完成");
                    finish();
                } else {
                    Toaster.show("提问失败");
                }
            }

            @Override
            public void onError(String message) {
                Toaster.show(TextUtils.isEmpty(message) ? "提问失败" : message);
            }
        });
    }

    private void updateBtn() {
        if (TextUtils.isEmpty(mContent.getText().toString())) {
            mBtnAdd.setEnabled(false);
        } else {
            mBtnAdd.setEnabled(true);
        }
    }

    private static class AddTextWatcher implements TextWatcher {
        private WeakReference<AddQuestionActivity> mFragment;

        public AddTextWatcher(AddQuestionActivity step2Fragment) {
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
            AddQuestionActivity step2 = mFragment.get();
            if (step2 == null) return;
            step2.updateBtn();
        }
    }
}
