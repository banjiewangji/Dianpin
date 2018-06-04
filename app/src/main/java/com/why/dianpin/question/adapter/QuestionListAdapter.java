package com.why.dianpin.question.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.R;
import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.question.holder.QuestionListItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListItemHolder> {

    private List<QuestionBean> mData = new ArrayList<>();

    public void setData(List<QuestionBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public QuestionListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_item_list_question_item, parent, false);
        return new QuestionListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionListItemHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            return;
        }

        QuestionBean bean = mData.get(position);
        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
