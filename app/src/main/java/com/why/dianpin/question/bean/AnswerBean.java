package com.why.dianpin.question.bean;

/**
 * @author shidefeng
 * @since 2018/5/14.
 */

public class AnswerBean {

    public int id;
    public String content;
    public long timestamp;

    public AnswerBean(int id, String content, long timestamp) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }
}
