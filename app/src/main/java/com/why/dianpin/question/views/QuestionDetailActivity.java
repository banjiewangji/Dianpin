package com.why.dianpin.question.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
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
import com.why.dianpin.util.LoginHelper;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class QuestionDetailActivity extends BaseActivity {

    public static final String DETAIL_ID = "detail_id";

    private RecyclerView mRecyclerView;
    private TextView mAnswerQuestion;
    private QuestionDetailAdapter mAdapter;
    private int mQuestionId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_question_detail);

        initViews();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        refreshData();
        initData();
    }

    private void initViews() {
        mRecyclerView = UIUtils.findView(this, R.id.recycler_view);
        mAnswerQuestion = UIUtils.findView(this, R.id.tv_answer_question);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("问题详情");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mQuestionId = getIntent().getIntExtra(DETAIL_ID, 0);

        mAdapter = new QuestionDetailAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAnswerQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginHelper.isLogin()) {
                    gotoAddAnswerActivity();
                } else {
                    LoginHelper.showLoginDialog(QuestionDetailActivity.this);
                }
            }
        });
    }

    private void gotoAddAnswerActivity() {
        Intent it = new Intent(QuestionDetailActivity.this, AddAnswerActivity.class);
        it.putExtra(AddAnswerActivity.QUESTION_ID, mQuestionId);
        QuestionDetailActivity.this.startActivity(it);
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("questionId",mQuestionId + "");
        HttpUtils.doPost("question/getQuestionDetail", params, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONObject scenic = result.optJSONObject("question");
                if (scenic == null) {
                    Toaster.show("数据为空");
                    return;
                }
                final QuestionBean question = QuestionBean.fromJson(scenic);
                QuestionDetailQuestionBean questionBean = new QuestionDetailQuestionBean();
                questionBean.question = question.question;

                List<IDetailBean> data = new ArrayList<>();
                data.add(questionBean);

                List<AnswerBean> answers = question.answers;
                QuestionDetailTipsBean tipsBean = new QuestionDetailTipsBean();
                tipsBean.answerNum = answers == null ? 0 : answers.size();
                data.add(tipsBean);

                if (answers != null) {
                    for (AnswerBean answer : answers) {
                        QuestionDetailItemBean itemBean = new QuestionDetailItemBean();
                        itemBean.answer = answer;
                        data.add(itemBean);
                    }
                }

                if (mAdapter != null) {
                    mAdapter.setData(data);
                }
            }

            @Override
            public void onError(String message) {
                Toaster.show(TextUtils.isEmpty(message) ? "获取列表失败" : message);
            }
        });
    }

    private QuestionBean getQuestion() {
        final QuestionBean bean = new QuestionBean();

        bean.question = "王华玥，I Love you";

        bean.timestamp = System.currentTimeMillis() - 10000000L;

        bean.author = new UserBean();
        bean.author.username = "石德峰";

        bean.answers = new ArrayList<>();
        bean.answers.add(new AnswerBean(1, "me, too", System.currentTimeMillis() - 30000L, new UserBean(0, 12, 1, "小仙女", "")));
        bean.answers.add(new AnswerBean(1, "me, too", System.currentTimeMillis() - 30000L, new UserBean(0, 12, 1, "小仙女", "")));
        bean.answers.add(new AnswerBean(1, "me, too", System.currentTimeMillis() - 30000L, new UserBean(0, 12, 1, "小仙女", "")));
        bean.answers.add(new AnswerBean(1, "me, too", System.currentTimeMillis() - 30000L, new UserBean(0, 12, 1, "小仙女", "")));
        bean.answers.add(new AnswerBean(1, "me, too", System.currentTimeMillis() - 30000L, new UserBean(0, 12, 1, "小仙女", "")));
        return bean;
    }

    private void refreshData() {
        final QuestionBean question = getQuestion();
        QuestionDetailQuestionBean questionBean = new QuestionDetailQuestionBean();
        questionBean.question = question.question;

        List<IDetailBean> data = new ArrayList<>();
        data.add(questionBean);

        QuestionDetailTipsBean tipsBean = new QuestionDetailTipsBean();
        tipsBean.answerNum = question.answers.size();
        data.add(tipsBean);

        for (AnswerBean answer : question.answers) {
            QuestionDetailItemBean itemBean = new QuestionDetailItemBean();
            itemBean.answer = answer;
            data.add(itemBean);
        }

        if (mAdapter != null) {
            mAdapter.setData(data);
        }
    }
}
