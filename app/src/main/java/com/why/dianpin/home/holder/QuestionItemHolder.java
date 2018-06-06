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
import com.why.dianpin.question.holder.QuestionListItemHolder;
import com.why.dianpin.question.views.QuestionListActivity;
import com.why.dianpin.travel.views.TravelDetailActivity;
import com.why.dianpin.travel.views.TravelListActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/9.
 */

public class QuestionItemHolder extends MainItemHolder<QuestionItem> {

    private final TextView mHeaderTitle;
    private final TextView mHeaderAllBtn;

    private List<QuestionListItemHolder> mHolders = new ArrayList<>();

    QuestionItemHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_list_question);

        mHeaderTitle = findView(itemView, R.id.item_header_title);
        mHeaderAllBtn = findView(itemView, R.id.item_header_all);

        mHolders.add(new QuestionListItemHolder(findView(itemView, R.id.question_item_1)));
        mHolders.add(new QuestionListItemHolder(findView(itemView, R.id.question_item_2)));
        mHolders.add(new QuestionListItemHolder(findView(itemView, R.id.question_item_3)));

    }

    @Override
    public void setData(QuestionItem data) {
        mHeaderTitle.setText("问答");
        mHeaderAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, QuestionListActivity.class));
            }
        });

        List<QuestionBean> travels = data.questions;
        if (travels == null || travels.isEmpty()) {
            return;
        }

        final int beanSize = travels.size();
        final int viewSize = mHolders.size();

        final int min = Math.min(beanSize, viewSize);
        final int max = Math.max(beanSize, viewSize);

        for (int i = 0; i < max; i++) {
            if (i < min) {
                mHolders.get(i).setData(travels.get(i));
            } else {
                if (i < viewSize) {
                    mHolders.get(i).setData(null);
                }
            }
        }
    }
}
