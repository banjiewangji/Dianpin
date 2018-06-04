package com.why.dianpin.travel.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.travel.bean.TravelDetailHeaderBean;
import com.why.dianpin.util.ImageUtils;
import com.why.dianpin.util.UIUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaoyueyue
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
        ImageUtils.loadImage(itemView.getContext(), bean.imageUrl, mImages);

        mTitle.setText(bean.title);
        mAuthor.setText("作者：" + bean.author);
        mAuthor.setText("作者：" + bean.author);
        mDate.setText("日期\n\n" + dataFormat(bean.beginTime));
        mDuration.setText("天数\n\n" + bean.duration);
        mTeam.setText("同伴\n\n" + bean.team);
    }

    private String dataFormat(long timestamp) {
        final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(timestamp));
    }
}
