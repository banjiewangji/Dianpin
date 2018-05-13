package com.why.dianpin.scenic.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.dianpin.R;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.scenic.views.StarsView;
import com.why.dianpin.util.IamgeUtils;
import com.why.dianpin.util.UIUtils;

import java.text.DecimalFormat;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class ScenicListItemHolder extends RecyclerView.ViewHolder {

    private final TextView mTitle;
    private final TextView mRating;
    private final TextView mSubTitle;
    private final ImageView mImage;
    private final StarsView mStars;
    private final TextView mGrade;
    private final TextView mComment;
    private final TextView mLocation;

    public ScenicListItemHolder(View itemView) {
        super(itemView);
        mTitle = UIUtils.findView(itemView, R.id.item_scenic_list_title);
        mRating = UIUtils.findView(itemView, R.id.item_scenic_list_rating);
        mSubTitle = UIUtils.findView(itemView, R.id.item_scenic_list_subtitle);
        mImage = UIUtils.findView(itemView, R.id.item_scenic_list_image);
        mStars = UIUtils.findView(itemView, R.id.item_scenic_list_stars);
        mGrade = UIUtils.findView(itemView, R.id.item_scenic_list_grade);
        mComment = UIUtils.findView(itemView, R.id.item_scenic_list_comment);
        mLocation = UIUtils.findView(itemView, R.id.item_scenic_list_location);
    }

    public void setData(ScenicListBean bean) {
        if (bean == null) {
          return;
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTitle.setText(bean.title);
        mRating.setText("(" + bean.rating + ")");
        mSubTitle.setText(bean.subTitle);
        mGrade.setText(bean.grade + "分");
        mComment.setText("| " + bean.commentCount + "人评价");
        mLocation.setText(bean.location);

        mStars.update(bean.grade);
        IamgeUtils.loadImage(itemView.getContext(), bean.imageUrl, mImage);
    }

    private float handleDecimal(double number) {
        final DecimalFormat format = new DecimalFormat("#.0");
        return Float.parseFloat(format.format(number));
    }
}
