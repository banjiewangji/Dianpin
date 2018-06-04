package com.why.dianpin.question.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.scenic.bean.ScenicDetailHeaderBean;
import com.why.dianpin.scenic.bean.ScenicDetailItemBean;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.travel.bean.IDetailBean;
import com.why.dianpin.util.HttpUtils;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class AddAnswerActivity extends BaseActivity {

    public static final String DETAIL_ID = "detail_id";

    private EditText mContent;
    private TextView mBtnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_add_answer);

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
                Toaster.show("提交成功");
            }
        });
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("scenicId", getIntent().getIntExtra(DETAIL_ID, 0) + "");
        HttpUtils.doPost("scenic/getScenicsDetail", params, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONObject scenic = result.optJSONObject("scenic");
                if (scenic == null) {
                    Toaster.show("数据为空");
                    return;
                }
                final ScenicListBean scenicDetail = ScenicListBean.fromJson(scenic);
                final ScenicDetailHeaderBean headerBean = new ScenicDetailHeaderBean();
                ScenicListBean.copy(headerBean, scenicDetail);

                List<IDetailBean> data = new ArrayList<>();
                data.add(headerBean);
                final String detail = scenicDetail.detail;
                final String[] splits = detail.split("\\$\\$");
                for (String str : splits) {
                    str = str.replace("\\n", "\n");
                    data.add(new ScenicDetailItemBean(str.startsWith("http") ? IDetailBean.TYPE_ITEM_IMAGE : IDetailBean.TYPE_ITEM_TEXT, str));
                }
            }

            @Override
            public void onError(String message) {
                Toaster.show(TextUtils.isEmpty(message) ? "获取列表失败" : message);
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
        private WeakReference<AddAnswerActivity> mFragment;

        public AddTextWatcher(AddAnswerActivity step2Fragment) {
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
            AddAnswerActivity step2 = mFragment.get();
            if (step2 == null) return;
            step2.updateBtn();
        }
    }
}
