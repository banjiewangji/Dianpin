package com.why.dianpin.question.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.question.views.QuestionDetailActivity;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.scenic.views.ScenicDetailActivity;
import com.why.dianpin.scenic.views.StarsView;
import com.why.dianpin.travel.views.TravelDetailActivity;
import com.why.dianpin.util.ImageUtils;
import com.why.dianpin.util.UIUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class QuestionListItemHolder extends RecyclerView.ViewHolder {

    private final View itemView;
    private final TextView mQuestion;
    private final TextView mAnswer;
    private final TextView mDate;
    private final TextView mLook;

    public QuestionListItemHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        mQuestion = UIUtils.findView(itemView, R.id.tv_question_question);
        mAnswer = UIUtils.findView(itemView, R.id.tv_question_answer);
        mDate = UIUtils.findView(itemView, R.id.tv_question_date);
        mLook = UIUtils.findView(itemView, R.id.tv_question_look);
    }

    public void setData(QuestionBean question) {
        if (question == null) {
            return;
        }

        mQuestion.setText(question.question);
        if (question.answers != null && question.answers.size() > 0) {
            mAnswer.setText(question.answers.get(0).content);
        }

        mDate.setText(dataFormat(question.timestamp));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemView.getContext().startActivity(new Intent(itemView.getContext(), QuestionDetailActivity.class));
            }
        });

        mLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemView.getContext().startActivity(new Intent(itemView.getContext(), QuestionDetailActivity.class));
            }
        });
    }

    private String dataFormat(long timestamp) {
        final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(timestamp));
    }
}
