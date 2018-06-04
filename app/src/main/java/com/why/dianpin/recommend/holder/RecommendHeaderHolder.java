package com.why.dianpin.recommend.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.recommend.bean.RecommendDetailHeaderBean;
import com.why.dianpin.scenic.bean.ScenicDetailHeaderBean;
import com.why.dianpin.util.UIUtils;

/**
 * @author xiaoyueyue
 * @since 2018/5/10.
 */

public class RecommendHeaderHolder extends RecyclerView.ViewHolder {

    private final ImageView mImages;
    private final TextView mTitle;
    private final TextView mSubTitle;

    public RecommendHeaderHolder(View itemView) {
        super(itemView);
        mTitle = UIUtils.findView(itemView, R.id.item_travel_detail_header_title);
        mSubTitle = UIUtils.findView(itemView, R.id.item_travel_detail_header_subtitle);
        mImages = UIUtils.findView(itemView, R.id.item_travel_detail_header_image);
    }

    public void setData(RecommendDetailHeaderBean bean) {
        Glide.with(itemView.getContext()).load(bean.imageUrl).into(mImages);

        mTitle.setText(bean.title);
        mSubTitle.setText(bean.subTitle);
    }
}
