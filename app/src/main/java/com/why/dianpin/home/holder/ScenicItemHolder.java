package com.why.dianpin.home.holder;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.Scenic;
import com.why.dianpin.home.beans.ScenicItem;
import com.why.dianpin.scenic.views.ScenicDetailActivity;
import com.why.dianpin.scenic.views.ScenicListActivity;

import java.util.List;

/**
 * @author shidefeng
 * @since 2018/4/1.
 */

public class ScenicItemHolder extends MainItemHolder<ScenicItem> implements View.OnClickListener {

    private static final int[] TIPS_BG_COLOR = {0xFF33CCFF, 0xFFFF3030, 0xFFFFB90F};

    private final TextView mHeaderTitle;
    private final TextView mHeaderAllBtn;
    private final LinearLayout mScenicList;

    ScenicItemHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_list_scenic);

        mHeaderTitle = findView(itemView, R.id.item_header_title);
        mHeaderAllBtn = findView(itemView, R.id.item_header_all);
        mScenicList = findView(itemView, R.id.scenic_list);
    }

    @Override
    public void setData(final ScenicItem data) {

        mHeaderTitle.setText("必去景点");
        mHeaderAllBtn.setText("查看全部");
        mHeaderAllBtn.setOnClickListener(this);

        if (data.scenics == null) {
            mScenicList.removeAllViews();
            return;
        }
        int scenicSize = data.scenics.size();

        for (int i = 0; i < scenicSize; i++) {
            View convertView;
            if (i >= mScenicList.getChildCount()) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_scenic_item, null);
                mScenicList.addView(convertView);
            } else {
                convertView = mScenicList.getChildAt(i);
                convertView.setVisibility(View.VISIBLE);
            }

            convertView.setTag(R.id.scenic_item_position, i);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag(R.id.scenic_item_position);
                    List<Scenic> scenics = data.scenics;
                    if (scenics != null && !scenics.isEmpty()) {
                        mContext.startActivity(new Intent(mContext, ScenicDetailActivity.class));
                    }
                }
            });

            ImageView mImage = findView(convertView, R.id.item_scenic_image);
            TextView mTips = findView(convertView, R.id.item_scenic_tips);
            TextView mTitle = findView(convertView, R.id.item_scenic_title);

            final Scenic scenic = data.scenics.get(i);

            if (scenic == null) {
                return;
            }
            mImage.setImageResource(scenic.icon);
            mTitle.setText(scenic.title);

            if (i < 3) {
                GradientDrawable bg = (GradientDrawable) mTips.getBackground();
                bg = (GradientDrawable) bg.mutate();
                bg.setColor(TIPS_BG_COLOR[i]);
                mTips.setBackgroundDrawable(bg);

                mTips.setVisibility(View.VISIBLE);
                mTips.setText("TOP." + (i + 1));
            } else {
                mTips.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.item_header_all) {
            mContext.startActivity(new Intent(mContext, ScenicListActivity.class));
        }
    }
}
