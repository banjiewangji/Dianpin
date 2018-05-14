package com.why.dianpin.recommend.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.R;
import com.why.dianpin.recommend.bean.RecommendDetailHeaderBean;
import com.why.dianpin.recommend.bean.RecommendDetailItemBean;
import com.why.dianpin.recommend.holder.RecommendHeaderHolder;
import com.why.dianpin.recommend.holder.RecommendItemTextHolder;
import com.why.dianpin.travel.bean.IDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class RecommendDetailAdapter extends RecyclerView.Adapter {

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
                view = inflater.inflate(R.layout.item_recommend_detail_header, parent, false);
                return new RecommendHeaderHolder(view);
            case IDetailBean.TYPE_ITEM_TEXT:
            default:
                view = inflater.inflate(R.layout.item_detail_item_text, parent, false);
                return new RecommendItemTextHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            return;
        }

        IDetailBean bean = mData.get(position);

        if (holder instanceof RecommendHeaderHolder) {
            ((RecommendHeaderHolder) holder).setData((RecommendDetailHeaderBean) bean);
        } else if (holder instanceof RecommendItemTextHolder) {
            ((RecommendItemTextHolder) holder).setData((RecommendDetailItemBean) bean);
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
