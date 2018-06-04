package com.why.dianpin.recommend.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.R;
import com.why.dianpin.recommend.bean.RecommendListBean;
import com.why.dianpin.recommend.holder.RecommendListItemHolder;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.scenic.holder.ScenicListItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListItemHolder> {

    private List<RecommendListBean> mData = new ArrayList<>();

    public void setData(List<RecommendListBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecommendListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_list, parent, false);
        return new RecommendListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendListItemHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            return;
        }

        RecommendListBean bean = mData.get(position);
        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
