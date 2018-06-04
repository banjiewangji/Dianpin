package com.why.dianpin.scenic.bean;

import com.why.dianpin.travel.bean.IDetailBean;

/**
 * @author xiaoyueyue
 * @since 2018/5/10.
 */

public class ScenicDetailItemBean implements IDetailBean {

    public int type = TYPE_ITEM_TEXT;
    public String text;

    public ScenicDetailItemBean(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public int getType() {
        return type;
    }
}
