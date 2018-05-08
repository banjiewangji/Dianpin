package com.why.dianpin.scenic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.R;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.scenic.holder.ScenicListItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class ScenicListAdapter extends RecyclerView.Adapter<ScenicListItemHolder> {

    private List<ScenicListBean> mData = new ArrayList<>();

    public void setData(List<ScenicListBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ScenicListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scenic_list, parent, false);
        return new ScenicListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ScenicListItemHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            return;
        }

        ScenicListBean bean = mData.get(position);
        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
