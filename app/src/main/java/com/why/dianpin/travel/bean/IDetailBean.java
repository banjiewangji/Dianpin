package com.why.dianpin.travel.bean;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public interface IDetailBean {

    int TYPE_HEADER = 0;
    int TYPE_ITEM_TEXT = 1;
    int TYPE_ITEM_IMAGE = 2;

    int TYPE_QUESTION = 3;
    int TYPE_ANSWER_TIPS = 4;
    int TYPE_ANSWER_ITEM = 5;

    int getType();
}
