package com.why.dianpin.home.holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.TravelsItem;
import com.why.dianpin.travel.bean.TravelBean;
import com.why.dianpin.travel.views.TravelDetailActivity;
import com.why.dianpin.travel.views.TravelListActivity;
import com.why.dianpin.util.ImageUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/9.
 */

public class TravelsItemHolder extends MainItemHolder<TravelsItem> {

    private final TextView mHeaderTitle;
    private final TextView mHeaderAllBtn;

    private List<TravelsViewHolder> mHolders = new ArrayList<>();

    TravelsItemHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_list_travels);

        mHeaderTitle = findView(itemView, R.id.item_header_title);
        mHeaderAllBtn = findView(itemView, R.id.item_header_all);

        mHolders.add(new TravelsViewHolder(findView(itemView, R.id.travels_item_1)));
        mHolders.add(new TravelsViewHolder(findView(itemView, R.id.travels_item_2)));
        mHolders.add(new TravelsViewHolder(findView(itemView, R.id.travels_item_3)));

    }

    @Override
    public void setData(TravelsItem data) {
        mHeaderTitle.setText("游记");
        mHeaderAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, TravelListActivity.class));
            }
        });

        List<TravelBean> travels = data.travels;
        if (travels == null || travels.isEmpty()) {
            return;
        }

        final int beanSize = travels.size();
        final int viewSize = mHolders.size();

        final int min = Math.min(beanSize, viewSize);
        final int max = Math.max(beanSize, viewSize);

        for (int i = 0; i < max; i++) {
            if (i < min) {
                mHolders.get(i).setData(travels.get(i));
            } else {
                if (i < viewSize) {
                    mHolders.get(i).setData(null);
                }
            }
        }
    }

    private static class TravelsViewHolder {

        private final View itemView;
        private final TextView mTips;
        private final ImageView mImage;
        private final TextView mContent;
        private final TextView mDesc;

        private TravelsViewHolder(View itemView) {

            this.itemView = itemView;
            mTips = findView(itemView, R.id.tv_travels_tips);
            mImage = findView(itemView, R.id.iv_travels_image);
            mContent = findView(itemView, R.id.tv_travels_content);
            mDesc = findView(itemView, R.id.tv_travels_description);
        }

        private void setData(final TravelBean travels) {
            if (travels == null) {
                itemView.setVisibility(View.GONE);
                return;
            }
            itemView.setVisibility(View.VISIBLE);
            ImageUtils.loadImage(itemView.getContext(), travels.imageUrl, mImage);
            mContent.setText(travels.title);
            String text = dataFormat(travels.beginTime) + "出游  " + travels.team + "  行程" + travels.duration + "天";
            mDesc.setText(text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = new Intent(itemView.getContext(), TravelDetailActivity.class);
                    intent.putExtra(TravelDetailActivity.DETAIL_ID, travels.id);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        private String dataFormat(long timestamp) {
            final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            return format.format(new Date(timestamp));
        }
    }
}
