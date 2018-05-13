package com.why.dianpin.travel.bean;

import org.json.JSONObject;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class TravelBean {

    public int id;              // id标识
    public String tags;         // 精华、热门等标签
    public String author;       // 作者
    public String title;        // 标题
    public String imageUrl;     // 封面
    public int seeCount;        // 浏览量
    public long beginTime;      // 出游时间
    public long createTime;     // 发帖时间
    public long duration;       // 旅行天数
    public String team;         // 同伴
    public String detail;       // 详情

    public TravelBean() {
    }

    public TravelBean(int id, String tags, String author, String title, String imageUrl, int seeCount, long beginTime, long createTime, long duration, String team) {
        this.id = id;
        this.tags = tags;
        this.author = author;
        this.title = title;
        this.imageUrl = imageUrl;
        this.seeCount = seeCount;
        this.beginTime = beginTime;
        this.createTime = createTime;
        this.duration = duration;
        this.team = team;
    }

    public static TravelBean fromJson(JSONObject json) {
        final TravelBean bean = new TravelBean();
        if (json == null) {
            return bean;
        }
        bean.id = json.optInt("id");
        bean.tags = json.optString("tags");
        bean.author = json.optString("author");
        bean.title = json.optString("title");
        bean.imageUrl = json.optString("imageUrl");
        bean.seeCount = json.optInt("seeCount");
        bean.beginTime = json.optLong("beginTime");
        bean.createTime = json.optLong("createTime");
        bean.duration = json.optLong("duration");
        bean.team = json.optString("team");
        bean.team = json.optString("detail");
        return bean;
    }

    public static void copy(TravelBean dst, TravelBean src) {
        dst.id = src.id;
        dst.tags = src.tags;
        dst.author = src.author;
        dst.title = src.title;
        dst.imageUrl = src.imageUrl;
        dst.seeCount = src.seeCount;
        dst.beginTime = src.beginTime;
        dst.createTime = src.createTime;
        dst.duration = src.duration;
        dst.team = src.team;
        dst.detail = src.detail;
    }
}
