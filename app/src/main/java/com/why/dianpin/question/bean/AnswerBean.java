package com.why.dianpin.question.bean;

import com.why.dianpin.user.bean.UserBean;

/**
 * @author xiaoyueyue
 * @since 2018/5/14.
 */

public class AnswerBean {

    public int id;
    public String content;
    public long timestamp;

    public UserBean author;

    public AnswerBean(int id, String content, long timestamp, UserBean author) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.author = author;
    }
}
