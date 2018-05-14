package com.why.dianpin.scenic.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.scenic.bean.ScenicDetailItemBean;
import com.why.dianpin.travel.bean.TravelDetailItemBean;
import com.why.dianpin.util.UIUtils;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public class ScenicItemImageHolder extends RecyclerView.ViewHolder {

    private final ImageView mImageView;

    public ScenicItemImageHolder(View itemView) {
        super(itemView);
        mImageView = UIUtils.findView(itemView, R.id.item_travel_detail_item_image);
    }

    public void setData(ScenicDetailItemBean bean) {
        Glide.with(itemView.getContext()).load(bean.text).into(mImageView);
    }

}
