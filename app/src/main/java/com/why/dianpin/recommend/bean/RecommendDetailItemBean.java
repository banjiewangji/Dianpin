package com.why.dianpin.recommend.bean;

import com.why.dianpin.travel.bean.IDetailBean;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public class RecommendDetailItemBean implements IDetailBean {

    public int type = TYPE_ITEM_TEXT;
    public String text;

    public RecommendDetailItemBean(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public int getType() {
        return type;
    }
}
