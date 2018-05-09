package com.why.dianpin.travel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.R;
import com.why.dianpin.travel.bean.TravelListBean;
import com.why.dianpin.travel.holder.TravelListItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class TravelListAdapter extends RecyclerView.Adapter<TravelListItemHolder> {

    private List<TravelListBean> mData = new ArrayList<>();

    public void setData(List<TravelListBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public TravelListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel_list, parent, false);
        return new TravelListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(TravelListItemHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            return;
        }

        TravelListBean bean = mData.get(position);
        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
