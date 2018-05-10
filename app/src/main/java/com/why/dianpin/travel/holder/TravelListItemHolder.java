package com.why.dianpin.travel.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.travel.bean.TravelBean;
import com.why.dianpin.util.UIUtils;

/**
 * @author shidefeng
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

    public void setData(TravelBean bean) {
        if (bean == null) {
          return;
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTags.setText(bean.tags);
        mAuthor.setText("作者：" + bean.author);
        mTitle.setText(bean.title);
        mSeeCount.setText("" + bean.seeCount);
        String text = "|  " + bean.beginTime + "出游  " + bean.team + "  行程" + bean.duration + "天";
        mTail.setText(text);

        Glide.with(itemView.getContext()).load(bean.imageUrl).into(mImage);
    }
}
