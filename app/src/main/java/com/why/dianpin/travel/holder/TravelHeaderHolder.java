package com.why.dianpin.travel.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.travel.bean.TravelDetailHeaderBean;
import com.why.dianpin.util.UIUtils;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public class TravelHeaderHolder extends RecyclerView.ViewHolder {

    private final ImageView mImages;
    private final TextView mTitle;
    private final TextView mAuthor;
    private final TextView mDate;
    private final TextView mDuration;
    private final TextView mTeam;

    public TravelHeaderHolder(View itemView) {
        super(itemView);
        mTitle = UIUtils.findView(itemView, R.id.item_travel_detail_header_title);
        mImages = UIUtils.findView(itemView, R.id.item_travel_detail_header_image);
        mAuthor = UIUtils.findView(itemView, R.id.item_travel_detail_header_author);
        mDate = UIUtils.findView(itemView, R.id.item_travel_detail_header_date);
        mDuration = UIUtils.findView(itemView, R.id.item_travel_detail_header_duration);
        mTeam = UIUtils.findView(itemView, R.id.item_travel_detail_header_team);
    }

    public void setData(TravelDetailHeaderBean bean) {
        Glide.with(itemView.getContext()).load(bean.imageUrl).into(mImages);

        mTitle.setText(bean.title);
        mAuthor.setText("作者：" + bean.author);
        mAuthor.setText("作者：" + bean.author);
        mDate.setText("日期\n\n" + bean.beginTime);
        mDuration.setText("天数\n\n" + bean.duration);
        mTeam.setText("同伴\n\n" + bean.team);
    }
}
