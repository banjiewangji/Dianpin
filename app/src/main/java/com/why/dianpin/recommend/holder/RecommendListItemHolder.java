package com.why.dianpin.recommend.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.recommend.bean.RecommendListBean;
import com.why.dianpin.recommend.views.RecommendDetailActivity;
import com.why.dianpin.util.ImageUtils;
import com.why.dianpin.util.UIUtils;

import java.text.DecimalFormat;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class RecommendListItemHolder extends RecyclerView.ViewHolder {

    private final TextView mTitle;
    private final TextView mSubTitle;
    private final ImageView mImage;
    private final TextView mSeeCount;

    public RecommendListItemHolder(View itemView) {
        super(itemView);
        mTitle = UIUtils.findView(itemView, R.id.item_recommend_list_title);
        mSubTitle = UIUtils.findView(itemView, R.id.item_recommend_list_subtitle);
        mImage = UIUtils.findView(itemView, R.id.item_recommend_list_image);
        mSeeCount = UIUtils.findView(itemView, R.id.item_recommend_list_seecount);
    }

    public void setData(final RecommendListBean bean) {
        if (bean == null) {
          return;
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), RecommendDetailActivity.class);
                intent.putExtra(RecommendDetailActivity.DETAIL_ID, bean.id);
                itemView.getContext().startActivity(intent);
            }
        });

        mTitle.setText(bean.title);
        mSubTitle.setText(bean.subTitle);
        mSeeCount.setText(bean.seeCount + "人");

        ImageUtils.loadImage(itemView.getContext(), bean.imageUrl, mImage);
    }

    private float handleDecimal(double number) {
        final DecimalFormat format = new DecimalFormat("#.0");
        return Float.parseFloat(format.format(number));
    }
}
