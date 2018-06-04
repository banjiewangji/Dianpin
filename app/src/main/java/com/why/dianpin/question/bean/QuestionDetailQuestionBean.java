package com.why.dianpin.question.bean;

import com.why.dianpin.travel.bean.IDetailBean;

/**
 * @author xiaoyueyue
 * @since 2018/5/15.
 */

public class QuestionDetailQuestionBean implements IDetailBean {

    public String question;

    @Override
    public int getType() {
        return TYPE_QUESTION;
    }
}
