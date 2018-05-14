package com.why.dianpin.home.holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.QuestionItem;
import com.why.dianpin.home.beans.Travels;
import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.travel.views.TravelDetailActivity;
import com.why.dianpin.travel.views.TravelListActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shidefeng
 * @since 2018/4/9.
 */

public class QuestionItemHolder extends MainItemHolder<QuestionItem> {

    private final TextView mHeaderTitle;
    private final TextView mHeaderAllBtn;

    private List<QuestionViewHolder> mHolders = new ArrayList<>();

    QuestionItemHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_list_question);

        mHeaderTitle = findView(itemView, R.id.item_header_title);
        mHeaderAllBtn = findView(itemView, R.id.item_header_all);

        mHolders.add(new QuestionViewHolder(findView(itemView, R.id.question_item_1)));
        mHolders.add(new QuestionViewHolder(findView(itemView, R.id.question_item_2)));
        mHolders.add(new QuestionViewHolder(findView(itemView, R.id.question_item_3)));

    }

    @Override
    public void setData(QuestionItem data) {
        mHeaderTitle.setText("问答");
        mHeaderAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, TravelListActivity.class));
            }
        });

        List<QuestionBean> travels = data.questions;
        if (travels == null || travels.isEmpty()) {
            return;
        }

        int count = Math.min(travels.size(), mHolders.size());
        for (int i = 0; i < count; i++) {
            mHolders.get(i).setData(travels.get(i));
        }
    }

    private static class QuestionViewHolder {

        private final View itemView;
        private final TextView mQuestion;
        private final TextView mAnswer;
        private final TextView mDate;
        private final TextView mLook;

        private QuestionViewHolder(View itemView) {

            this.itemView = itemView;
            mQuestion = findView(itemView, R.id.tv_question_question);
            mAnswer = findView(itemView, R.id.tv_question_answer);
            mDate = findView(itemView, R.id.tv_question_date);
            mLook = findView(itemView, R.id.tv_question_look);
        }

        private void setData(QuestionBean question) {
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
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), TravelDetailActivity.class));
                }
            });

            mLook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private String dataFormat(long timestamp) {
            final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            return format.format(new Date(timestamp));
        }
    }
}
