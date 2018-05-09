package com.why.dianpin.travel.bean;

import org.json.JSONObject;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class TravelListBean {

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

    public TravelListBean() {
    }

    public TravelListBean(int id, String tags, String author, String title, String imageUrl, int seeCount, long beginTime, long createTime, long duration, String team) {
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

    public static TravelListBean fromJson(JSONObject json) {
        final TravelListBean bean = new TravelListBean();
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
        return bean;
    }
}
