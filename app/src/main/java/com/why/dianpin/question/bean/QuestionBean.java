package com.why.dianpin.question.bean;

import com.why.dianpin.user.bean.UserBean;

import java.util.List;

/**
 * @author shidefeng
 * @since 2018/5/14.
 */

public class QuestionBean {

    public int id;
    public String question;
    public long timestamp;
    public List<AnswerBean> answers;

    public UserBean author;

}
