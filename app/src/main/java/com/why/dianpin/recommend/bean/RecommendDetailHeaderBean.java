package com.why.dianpin.recommend.bean;

import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.travel.bean.IDetailBean;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public class RecommendDetailHeaderBean extends RecommendListBean implements IDetailBean {

    public RecommendDetailHeaderBean() {
    }

    public RecommendDetailHeaderBean(int id, String title, String subTitle, String imageUrl, int seeCount, String detail) {
        super(id, title, subTitle, imageUrl, seeCount, detail);
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }


}
