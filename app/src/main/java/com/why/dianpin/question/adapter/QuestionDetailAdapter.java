package com.why.dianpin.question.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.R;
import com.why.dianpin.question.bean.QuestionDetailItemBean;
import com.why.dianpin.question.bean.QuestionDetailQuestionBean;
import com.why.dianpin.question.bean.QuestionDetailTipsBean;
import com.why.dianpin.question.holder.QuestionDetailItemHolder;
import com.why.dianpin.question.holder.QuestionDetailQuestionHolder;
import com.why.dianpin.question.holder.QuestionDetailTipsHolder;
import com.why.dianpin.scenic.bean.ScenicDetailHeaderBean;
import com.why.dianpin.scenic.bean.ScenicDetailItemBean;
import com.why.dianpin.scenic.holder.ScenicHeaderHolder;
import com.why.dianpin.scenic.holder.ScenicItemImageHolder;
import com.why.dianpin.scenic.holder.ScenicItemTextHolder;
import com.why.dianpin.travel.bean.IDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class QuestionDetailAdapter extends RecyclerView.Adapter {

    private List<IDetailBean> mData = new ArrayList<>();

    public void setData(List<IDetailBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case IDetailBean.TYPE_QUESTION:
                view = inflater.inflate(R.layout.item_question_detail_question, parent, false);
                return new QuestionDetailQuestionHolder(view);
            case IDetailBean.TYPE_ANSWER_TIPS:
                view = inflater.inflate(R.layout.item_question_detail_answer_tips, parent, false);
                return new QuestionDetailTipsHolder(view);
            case IDetailBean.TYPE_ANSWER_ITEM:
            default:
                view = inflater.inflate(R.layout.item_question_detail_answer_item, parent, false);
                return new QuestionDetailItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            return;
        }

        IDetailBean bean = mData.get(position);

        if (holder instanceof QuestionDetailQuestionHolder) {
            ((QuestionDetailQuestionHolder) holder).setData((QuestionDetailQuestionBean) bean);
        } else if (holder instanceof QuestionDetailTipsHolder) {
            ((QuestionDetailTipsHolder) holder).setData((QuestionDetailTipsBean) bean);
        } else if (holder instanceof QuestionDetailItemHolder) {
            ((QuestionDetailItemHolder) holder).setData((QuestionDetailItemBean) bean);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }
}
