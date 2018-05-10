package com.why.dianpin.travel.bean;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public class TravelDetailItemBean implements IDetailBean {

    public int type = TYPE_ITEM_TEXT;
    public String text;

    public TravelDetailItemBean(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public int getType() {
        return type;
    }
}
