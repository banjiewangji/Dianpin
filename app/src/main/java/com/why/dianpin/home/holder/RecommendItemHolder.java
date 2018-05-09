package com.why.dianpin.home.holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.Recommend;
import com.why.dianpin.home.beans.RecommendItem;
import com.why.dianpin.recommend.views.RecommendListActivity;
import com.why.dianpin.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidefeng
 * @since 2018/4/9.
 */

public class RecommendItemHolder extends MainItemHolder<RecommendItem> {

    private final TextView mHeaderTitle;
    private final TextView mHeaderAllBtn;
    private final View mContainer1;
    private final View mContainer2;

    private List<TextView> mItemViews;

    RecommendItemHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_list_recommend);

        mHeaderTitle = findView(itemView, R.id.item_header_title);
        mHeaderAllBtn = findView(itemView, R.id.item_header_all);

        mContainer1 = findView(itemView, R.id.rec_container1);
        mContainer2 = findView(itemView, R.id.rec_container2);

        mItemViews = new ArrayList<>();

        mItemViews.add((TextView) findView(itemView, R.id.rec_item1));
        mItemViews.add((TextView) findView(itemView, R.id.rec_item2));
        mItemViews.add((TextView) findView(itemView, R.id.rec_item3));
        mItemViews.add((TextView) findView(itemView, R.id.rec_item4));
        mItemViews.add((TextView) findView(itemView, R.id.rec_item5));
        mItemViews.add((TextView) findView(itemView, R.id.rec_item6));
    }

    @Override
    public void setData(RecommendItem data) {
        mHeaderTitle.setText("出行推荐");
        mHeaderAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, RecommendListActivity.class));
            }
        });

        List<Recommend> recommends = data.recommends;
        if (recommends == null || recommends.isEmpty()) {
            mContainer1.setVisibility(View.GONE);
            mContainer2.setVisibility(View.GONE);
            return;
        }

        int len = mItemViews.size();
        int size = Math.min(recommends.size(), len);

        if (size > 3) {
            mContainer1.setVisibility(View.VISIBLE);
            mContainer2.setVisibility(View.VISIBLE);
        } else {
            mContainer1.setVisibility(View.VISIBLE);
            mContainer2.setVisibility(View.GONE);
        }

        for (int i = 0; i < len; i++) {
            if (i < size) {
                Recommend r = recommends.get(i);
                TextView item = mItemViews.get(i);
                item.setVisibility(View.VISIBLE);
                item.setText(r.content);
            } else {
                mItemViews.get(i).setVisibility(View.INVISIBLE);
            }
        }

    }
}
