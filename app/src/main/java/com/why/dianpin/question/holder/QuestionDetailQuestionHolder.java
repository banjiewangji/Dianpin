package com.why.dianpin.question.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.question.bean.QuestionDetailQuestionBean;
import com.why.dianpin.travel.views.TravelDetailActivity;
import com.why.dianpin.util.UIUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class QuestionDetailQuestionHolder extends RecyclerView.ViewHolder {

    private final TextView mQuestion;

    public QuestionDetailQuestionHolder(View itemView) {
        super(itemView);

        mQuestion = UIUtils.findView(itemView, R.id.tv_question_question);
    }

    public void setData(QuestionDetailQuestionBean question) {
        if (question == null) {
            return;
        }

        mQuestion.setText(question.question);
    }
}
