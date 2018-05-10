package com.why.dianpin.travel.bean;

/**
 * @author shidefeng
 * @since 2018/5/10.
 */

public interface IDetailBean {

    int TYPE_HEADER = 0;
    int TYPE_ITEM_TEXT = 1;
    int TYPE_ITEM_IMAGE = 2;

    int getType();
}
