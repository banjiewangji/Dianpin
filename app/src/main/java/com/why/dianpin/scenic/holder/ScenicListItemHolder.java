package com.why.dianpin.scenic.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.why.dianpin.scenic.bean.ScenicListBean;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class ScenicListItemHolder extends RecyclerView.ViewHolder {

    public ScenicListItemHolder(View itemView) {
        super(itemView);
    }

    public void setData(ScenicListBean bean) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
