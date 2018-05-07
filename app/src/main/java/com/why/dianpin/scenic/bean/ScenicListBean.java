package com.why.dianpin.scenic.bean;

import org.json.JSONObject;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class ScenicListBean {

    public String title;        // 标题
    public String subTitle;     // 副标题
    public String imageUrl;     // 封面
    public int priority;        // 景区排名
    public String rating;       // 景区等级
    public double grade;        // 评分
    public int commentCount;    // 评论数
    public String location;     // 地区

    public ScenicListBean() {
    }

    public ScenicListBean(String title, String subTitle, String imageUrl, int priority, String rating, float star, int commentCount, String location) {
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.priority = priority;
        this.rating = rating;
        this.grade = star;
        this.commentCount = commentCount;
        this.location = location;
    }

    public static ScenicListBean fromJson(JSONObject json) {
        final ScenicListBean bean = new ScenicListBean();
        bean.title = json.optString("title");
        bean.subTitle = json.optString("subTitle");
        bean.imageUrl = json.optString("imageUrl");
        bean.priority = json.optInt("priority");
        bean.rating = json.optString("rating");
        bean.grade = json.optDouble("grade");
        bean.commentCount = json.optInt("commentCount");
        bean.location = json.optString("location");
        return bean;
    }
}
