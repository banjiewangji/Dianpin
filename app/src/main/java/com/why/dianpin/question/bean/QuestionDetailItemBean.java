package com.why.dianpin.question.bean;

import com.why.dianpin.travel.bean.IDetailBean;

/**
 * @author xiaoyueyue
 * @since 2018/5/15.
 */

public class QuestionDetailItemBean implements IDetailBean {

    public AnswerBean answer;

    @Override
    public int getType() {
        return TYPE_ANSWER_ITEM;
    }
}
