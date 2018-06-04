package com.why.dianpin.travel.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.travel.bean.TravelBean;
import com.why.dianpin.travel.views.TravelDetailActivity;
import com.why.dianpin.util.ImageUtils;
import com.why.dianpin.util.UIUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class TravelListItemHolder extends RecyclerView.ViewHolder {

    private final TextView mTags;
    private final ImageView mImage;
    private final TextView mAuthor;
    private final TextView mTitle;
    private final TextView mSeeCount;
    private final TextView mTail;

    public TravelListItemHolder(View itemView) {
        super(itemView);
        mTags = UIUtils.findView(itemView, R.id.item_travel_list_tags);
        mImage = UIUtils.findView(itemView, R.id.item_travel_list_image);
        mAuthor = UIUtils.findView(itemView, R.id.item_travel_list_author);
        mTitle = UIUtils.findView(itemView, R.id.item_travel_list_title);
        mSeeCount = UIUtils.findView(itemView, R.id.item_travel_list_seeCount);
        mTail = UIUtils.findView(itemView, R.id.item_travel_list_tail);
    }

    public void setData(final TravelBean bean) {
        if (bean == null) {
          return;
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), TravelDetailActivity.class);
                intent.putExtra(TravelDetailActivity.DETAIL_ID, bean.id);
                itemView.getContext().startActivity(intent);
            }
        });

        mTags.setText(bean.tags);
        mAuthor.setText("作者：" + bean.author);
        mTitle.setText(bean.title);
        mSeeCount.setText("" + bean.seeCount);
        String text = "|  " + dataFormat(bean.beginTime) + "出游  " + bean.team + "  行程" + bean.duration + "天";
        mTail.setText(text);

        ImageUtils.loadImage(itemView.getContext(), bean.imageUrl, mImage);
    }

    private String dataFormat(long timestamp) {
        final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(timestamp));
    }
}
