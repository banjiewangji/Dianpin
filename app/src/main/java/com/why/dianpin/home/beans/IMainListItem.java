package com.why.dianpin.home.beans;

/**
 * @author shidefeng
 * @since 2018/4/1.
 */

public interface IMainListItem {

    int TYPE_DEFAULT = -1;      // 无数据
    int TYPE_CATEGORY = 1;      // 分类
    int TYPE_SCENIC = 2;        // 必去景点
    int TYPE_RECOMMEND = 3;     // 出行宝典
    int TYPE_TRAVELS = 4;       // 游记
    int TYPE_MAP = 5;           // 地图
    int TYPE_QUESTION = 6;      // 问答

    int getItemType();

}
