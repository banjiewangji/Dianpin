package com.why.dianpin.home;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.why.dianpin.home.beans.IMainListItem;
import com.why.dianpin.home.holder.HolderFactory;
import com.why.dianpin.home.holder.MainItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王华玥
 * @since 2018/3/31.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainItemHolder> {

    private List<IMainListItem> mData = new ArrayList<>();

    public MainListAdapter() {
    }

    public void setData(List<IMainListItem> data) {
        if (mData.size() > 0) {
            mData.clear();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MainItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HolderFactory.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(MainItemHolder holder, int position) {
        if (position >= mData.size()) {
            return;
        }
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mData.size()) {
            return IMainListItem.TYPE_DEFAULT;
        } else {
            return mData.get(position).getItemType();
        }
    }
}
