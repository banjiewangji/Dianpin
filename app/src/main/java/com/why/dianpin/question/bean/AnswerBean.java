package com.why.dianpin.question.bean;

import com.why.dianpin.user.bean.UserBean;

import org.json.JSONObject;

/**
 * @author xiaoyueyue
 * @since 2018/5/14.
 */

public class AnswerBean {

    public int id;
    public String content;
    public long timestamp;

    public UserBean author;

    public AnswerBean() {}

    public AnswerBean(int id, String content, long timestamp, UserBean author) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.author = author;
    }

    public static AnswerBean fromJson(JSONObject json) {
        final AnswerBean bean = new AnswerBean();
        if (json == null) {
            return bean;
        }
        bean.id = json.optInt("id");
        bean.content = json.optString("content");
        bean.timestamp = json.optLong("timestamp");

        JSONObject author = json.optJSONObject("author");
        if (author != null) {
            bean.author = UserBean.fromJson(author);
        }
        return bean;
    }
}
