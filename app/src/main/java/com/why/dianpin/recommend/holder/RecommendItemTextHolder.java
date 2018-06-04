package com.why.dianpin.recommend.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.recommend.bean.RecommendDetailItemBean;
import com.why.dianpin.scenic.bean.ScenicDetailItemBean;
import com.why.dianpin.util.UIUtils;

/**
 * @author xiaoyueyue
 * @since 2018/5/10.
 */

public class RecommendItemTextHolder extends RecyclerView.ViewHolder {

    private final TextView mText;

    public RecommendItemTextHolder(View itemView) {
        super(itemView);
        mText = UIUtils.findView(itemView, R.id.item_travel_detail_item_text);
    }

    public void setData(RecommendDetailItemBean bean) {
        mText.setText(bean.text);
    }

}
