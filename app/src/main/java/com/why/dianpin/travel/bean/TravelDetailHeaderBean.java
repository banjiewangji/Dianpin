package com.why.dianpin.travel.bean;

/**
 * @author xiaoyueyue
 * @since 2018/5/10.
 */

public class TravelDetailHeaderBean extends TravelBean implements IDetailBean {

    public TravelDetailHeaderBean() {
    }

    public TravelDetailHeaderBean(int id, String tags, String author, String title, String imageUrl, int seeCount, long beginTime, long createTime, long duration, String team) {
        super(id, tags, author, title, imageUrl, seeCount, beginTime, createTime, duration, team);
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }
}
