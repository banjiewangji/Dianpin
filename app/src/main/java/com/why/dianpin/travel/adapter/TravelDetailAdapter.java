package com.why.dianpin.travel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.R;
import com.why.dianpin.travel.bean.IDetailBean;
import com.why.dianpin.travel.bean.TravelDetailHeaderBean;
import com.why.dianpin.travel.bean.TravelDetailItemBean;
import com.why.dianpin.travel.holder.TravelHeaderHolder;
import com.why.dianpin.travel.holder.TravelItemImageHolder;
import com.why.dianpin.travel.holder.TravelItemTextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class TravelDetailAdapter extends RecyclerView.Adapter {

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
            case IDetailBean.TYPE_HEADER:
                view = inflater.inflate(R.layout.item_travel_detail_header, parent, false);
                return new TravelHeaderHolder(view);
            case IDetailBean.TYPE_ITEM_IMAGE:
                view = inflater.inflate(R.layout.item_detail_item_image, parent, false);
                return new TravelItemImageHolder(view);
            case IDetailBean.TYPE_ITEM_TEXT:
            default:
                view = inflater.inflate(R.layout.item_detail_item_text, parent, false);
                return new TravelItemTextHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            return;
        }

        IDetailBean bean = mData.get(position);

        if (holder instanceof TravelHeaderHolder) {
            ((TravelHeaderHolder) holder).setData((TravelDetailHeaderBean) bean);
        } else if (holder instanceof TravelItemImageHolder) {
            ((TravelItemImageHolder) holder).setData((TravelDetailItemBean) bean);
        } else if (holder instanceof TravelItemTextHolder) {
            ((TravelItemTextHolder) holder).setData((TravelDetailItemBean) bean);
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
