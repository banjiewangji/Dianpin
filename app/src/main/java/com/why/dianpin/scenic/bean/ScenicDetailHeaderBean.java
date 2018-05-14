package com.why.dianpin.scenic.bean;

import com.why.dianpin.travel.bean.IDetailBean;
import com.why.dianpin.travel.bean.TravelBean;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public class ScenicDetailHeaderBean extends ScenicListBean implements IDetailBean {

    public ScenicDetailHeaderBean() {
    }

    public ScenicDetailHeaderBean(int id, String title, String subTitle, String imageUrl, int priority, String rating, double star, int commentCount, String location, String detail, double price) {
        super(id, title, subTitle, imageUrl, priority, rating, star, commentCount, location, detail, price);
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }
}
