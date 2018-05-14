package com.why.dianpin.scenic.bean;

import org.json.JSONObject;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class ScenicListBean {

    public int id;              // id标识
    public String title;        // 标题
    public String subTitle;     // 副标题
    public String imageUrl;     // 封面
    public int priority;        // 景区排名
    public String rating;       // 景区等级
    public double grade;        // 评分
    public int commentCount;    // 评论数
    public String location;     // 地区
    public String detail;       // 详情
    public double price;        // 价格

    public ScenicListBean() {
    }

    public ScenicListBean(int id, String title, String subTitle, String imageUrl, int priority, String rating, double star, int commentCount, String location, String detail, double price) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.priority = priority;
        this.rating = rating;
        this.grade = star;
        this.commentCount = commentCount;
        this.location = location;
        this.detail = detail;
        this.price = price;
    }

    public static ScenicListBean fromJson(JSONObject json) {
        final ScenicListBean bean = new ScenicListBean();
        if (json == null) {
            return bean;
        }
        bean.id = json.optInt("id");
        bean.title = json.optString("title");
        bean.subTitle = json.optString("subTitle");
        bean.imageUrl = json.optString("imageUrl");
        bean.priority = json.optInt("priority");
        bean.rating = json.optString("rating");
        bean.grade = json.optDouble("grade");
        bean.commentCount = json.optInt("commentCount");
        bean.location = json.optString("location");
        bean.detail = json.optString("detail");
        bean.price = json.optDouble("price");
        return bean;
    }

    public static void copy(ScenicListBean dst, ScenicListBean src) {
        dst.id = src.id;
        dst.title = src.title;
        dst.subTitle = src.subTitle;
        dst.imageUrl = src.imageUrl;
        dst.priority = src.priority;
        dst.rating = src.rating;
        dst.grade = src.grade;
        dst.commentCount = src.commentCount;
        dst.location = src.location;
        dst.detail = src.detail;
        dst.price = src.price;
    }
}
