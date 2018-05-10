package com.why.dianpin.home.holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.Travels;
import com.why.dianpin.home.beans.TravelsItem;
import com.why.dianpin.home.listener.MainItemClickListener;
import com.why.dianpin.travel.views.TravelDetailActivity;
import com.why.dianpin.travel.views.TravelListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidefeng
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

        List<Travels> travels = data.travels;
        if (travels == null || travels.isEmpty()) {
            return;
        }

        int count = Math.min(travels.size(), mHolders.size());
        for (int i = 0; i < count; i++) {
            mHolders.get(i).setData(travels.get(i));
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

        private void setData(Travels travels) {
            if (travels == null) {
                return;
            }

            mImage.setImageResource(travels.image);
            mContent.setText(travels.content);
            mDesc.setText(travels.desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), TravelDetailActivity.class));
                }
            });
        }
    }
}
