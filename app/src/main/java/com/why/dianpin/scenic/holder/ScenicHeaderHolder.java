package com.why.dianpin.scenic.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.scenic.bean.ScenicDetailHeaderBean;
import com.why.dianpin.travel.bean.TravelDetailHeaderBean;
import com.why.dianpin.util.UIUtils;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public class ScenicHeaderHolder extends RecyclerView.ViewHolder {

    private final ImageView mImages;
    private final TextView mTitle;
    private final TextView mSubTitle;
    private final TextView mDate;
    private final TextView mDuration;
    private final TextView mTeam;

    public ScenicHeaderHolder(View itemView) {
        super(itemView);
        mTitle = UIUtils.findView(itemView, R.id.item_travel_detail_header_title);
        mSubTitle = UIUtils.findView(itemView, R.id.item_travel_detail_header_subtitle);
        mImages = UIUtils.findView(itemView, R.id.item_travel_detail_header_image);
        mDate = UIUtils.findView(itemView, R.id.item_travel_detail_header_date);
        mDuration = UIUtils.findView(itemView, R.id.item_travel_detail_header_duration);
        mTeam = UIUtils.findView(itemView, R.id.item_travel_detail_header_team);
    }

    public void setData(ScenicDetailHeaderBean bean) {
        Glide.with(itemView.getContext()).load(bean.imageUrl).into(mImages);

        mTitle.setText(bean.title);
        mSubTitle.setText(bean.subTitle);
        mDate.setText("等级\n\n" + bean.rating);
        mDuration.setText("门票\n\n" + bean.price);
        mTeam.setText("位置\n\n" + bean.location);
    }
}
