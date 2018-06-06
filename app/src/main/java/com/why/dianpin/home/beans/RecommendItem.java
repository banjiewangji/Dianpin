package com.why.dianpin.home.beans;

import com.why.dianpin.recommend.bean.RecommendListBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/9.
 */

public class RecommendItem extends IMainListItem {

    public List<RecommendListBean> recommends;

    @Override
    public int getItemType() {
        return TYPE_RECOMMEND;
    }

    @Override
    public void getDataList(JSONArray array) {
        recommends = new ArrayList<>();
        if (array == null || array.length() <= 0) {
            return;
        }
        try {
            for (int i = 0; i < array.length(); i++) {
                final JSONObject json = array.getJSONObject(i);
                final RecommendListBean bean = RecommendListBean.fromJson(json);
                if (bean != null) {
                    recommends.add(bean);
                }
            }
        } catch (JSONException ignore) {

        }

    }
}
