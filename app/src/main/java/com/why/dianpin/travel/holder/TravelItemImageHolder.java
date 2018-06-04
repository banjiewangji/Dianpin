package com.why.dianpin.travel.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.travel.bean.TravelDetailHeaderBean;
import com.why.dianpin.travel.bean.TravelDetailItemBean;
import com.why.dianpin.util.ImageUtils;
import com.why.dianpin.util.UIUtils;

/**
 * @author xiaoyueyue
 * @since 2018/5/10.
 */

public class TravelItemImageHolder extends RecyclerView.ViewHolder {

    private final ImageView mImageView;

    public TravelItemImageHolder(View itemView) {
        super(itemView);
        mImageView = UIUtils.findView(itemView, R.id.item_travel_detail_item_image);
    }

    public void setData(TravelDetailItemBean bean) {
        ImageUtils.loadImage(itemView.getContext(), bean.text, mImageView);
    }

}
