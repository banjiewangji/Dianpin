package com.why.dianpin.recommend.bean;

import org.json.JSONObject;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class RecommendListBean {

    public int id;              // id标识
    public String title;        // 标题
    public String subTitle;     // 副标题
    public String imageUrl;     // 封面
    public int seeCount;        // 查看数
    public String detail;       // 详情

    public RecommendListBean() {
    }

    public RecommendListBean(int id, String title, String subTitle, String imageUrl, int seeCount, String detail) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.seeCount = seeCount;
        this.detail = detail;
    }

    public static RecommendListBean fromJson(JSONObject json) {
        final RecommendListBean bean = new RecommendListBean();
        if (json == null) {
            return bean;
        }
        bean.id = json.optInt("id");
        bean.title = json.optString("title");
        bean.subTitle = json.optString("subTitle");
        bean.imageUrl = json.optString("imageUrl");
        bean.seeCount = json.optInt("seeCount");
        bean.detail = json.optString("detail");
        return bean;
    }

    public static void copy(RecommendListBean dst, RecommendListBean src) {
        dst.id = src.id;
        dst.title = src.title;
        dst.subTitle = src.subTitle;
        dst.imageUrl = src.imageUrl;
        dst.seeCount = src.seeCount;
        dst.detail = src.detail;
    }
}
