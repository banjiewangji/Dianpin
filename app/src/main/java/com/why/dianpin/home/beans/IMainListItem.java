package com.why.dianpin.home.beans;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author xiaoyueyue
 * @since 2018/4/1.
 */

public abstract class IMainListItem {

    public static final int TYPE_DEFAULT = -1;      // 无数据
    public static final int TYPE_CATEGORY = 0;      // 分类
    public static final int TYPE_RECOMMEND = 1;     // 出行宝典
    public static final int TYPE_SCENIC = 2;        // 必去景点
    public static final int TYPE_TRAVELS = 3;       // 游记
    public static final int TYPE_MAP = 4;           // 地图
    public static final int TYPE_QUESTION = 5;      // 问答

    public int id;
    public int type;
    public String typeName;
    public String tipsName;
    public int showPriority;
    public int showStatus;

    public abstract int getItemType();

    public abstract void getDataList(JSONArray array);

    public static IMainListItem fromJson(JSONObject json) {
        if (json == null) {
            return null;
        }
        IMainListItem item = null;
        final int type = json.optInt("type");
        switch (type) {
            case TYPE_CATEGORY:
                item = new CategoryItem();
                break;
            case TYPE_RECOMMEND:
                item = new RecommendItem();
                break;
            case TYPE_SCENIC:
                item = new ScenicItem();
                break;
            case TYPE_TRAVELS:
                item = new TravelsItem();
                break;
            case TYPE_MAP:
                item = new MapItem();
                break;
            case TYPE_QUESTION:
                item = new QuestionItem();
                break;

        }
        item.id = json.optInt("id");
        item.type = type;
        item.typeName = json.optString("typeName");
        item.tipsName = json.optString("tipsName");
        item.showPriority = json.optInt("showPriority");
        item.showStatus = json.optInt("showStatus");
        item.getDataList(json.optJSONArray("dataList"));

        return item;
    }

}
