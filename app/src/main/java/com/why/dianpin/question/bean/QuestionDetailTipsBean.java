package com.why.dianpin.question.bean;

import com.why.dianpin.travel.bean.IDetailBean;

/**
 * @author shidefeng
 * @since 2018/5/15.
 */

public class QuestionDetailTipsBean implements IDetailBean {

    public int answerNum;

    @Override
    public int getType() {
        return TYPE_ANSWER_TIPS;
    }
}
