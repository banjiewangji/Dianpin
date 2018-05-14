package com.why.dianpin.home.beans;

import com.why.dianpin.question.bean.QuestionBean;

import java.util.List;

/**
 * @author shidefeng
 * @since 2018/5/14.
 */

public class QuestionItem implements IMainListItem {

    public List<QuestionBean> questions;

    @Override
    public int getItemType() {
        return TYPE_QUESTION;
    }
}
