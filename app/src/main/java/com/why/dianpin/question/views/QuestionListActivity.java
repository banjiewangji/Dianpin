package com.why.dianpin.question.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.question.adapter.QuestionListAdapter;
import com.why.dianpin.question.bean.AnswerBean;
import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.user.bean.UserBean;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import java.util.ArrayList;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class QuestionListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private QuestionListAdapter mAdapter;
    private TextView mAskQuestion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_question_list);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        mRecyclerView = UIUtils.findView(this, R.id.recycler_view);
        mAskQuestion = UIUtils.findView(this, R.id.tv_ask_question);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("问答");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAdapter = new QuestionListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAskQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAddQuestionActivity();
            }
        });
    }

    private void gotoAddQuestionActivity() {
        Intent intent = new Intent(QuestionListActivity.this, AddQuestionActivity.class);
        startActivity(intent);
    }

    private void initData() {
//        HttpUtil.create("scenic/getScenicList")
//                .get(new HttpUtil.HttpCallback() {
//                    @Override
//                    public void onSuccess(JSONObject result) {
//                        final JSONArray scenicList = result.optJSONArray("scenics");
//                        final ArrayList<ScenicListBean> beans = new ArrayList<>();
//                        for (int i = 0, len = scenicList.length(); i < len; i++) {
//                            beans.add(ScenicListBean.fromJson(scenicList.optJSONObject(i)));
//                        }
//                        if (mAdapter != null) {
//                            mAdapter.setData(beans);
//                        }
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        Toaster.show(TextUtils.isEmpty(message) ? "获取列表失败" : message);
//                    }
//                });

        final ArrayList<QuestionBean> beans = new ArrayList<>();
        beans.add(getQuestionBean());
        beans.add(getQuestionBean());
        beans.add(getQuestionBean());
        beans.add(getQuestionBean());
        beans.add(getQuestionBean());
        beans.add(getQuestionBean());
        beans.add(getQuestionBean());

        mAdapter.setData(beans);
    }

    @NonNull
    private QuestionBean getQuestionBean() {
        final QuestionBean question = new QuestionBean();
        question.timestamp = System.currentTimeMillis();
        question.question = "北京菜什么味道，南方人吃得惯吗";
        question.answers = new ArrayList<>();
        question.answers.add(new AnswerBean(0, "还行吧", System.currentTimeMillis(), null));
        question.answers.add(new AnswerBean(1, "哈哈哈，一点也不好吃", System.currentTimeMillis(), null));
        question.author = new UserBean();
        question.author.username = "fengfengfegn";
        question.author.id = 3;
        return question;
    }
}
