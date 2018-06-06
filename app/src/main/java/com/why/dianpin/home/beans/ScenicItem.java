package com.why.dianpin.home.beans;

import com.why.dianpin.recommend.bean.RecommendListBean;
import com.why.dianpin.scenic.bean.ScenicListBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/1.
 */

public class ScenicItem extends IMainListItem {

    public List<ScenicListBean> scenics;

    @Override
    public int getItemType() {
        return TYPE_SCENIC;
    }

    @Override
    public void getDataList(JSONArray array) {
        scenics = new ArrayList<>();
        if (array == null || array.length() <= 0) {
            return;
        }
        try {
            for (int i = 0; i < array.length(); i++) {
                final JSONObject json = array.getJSONObject(i);
                final ScenicListBean bean = ScenicListBean.fromJson(json);
                if (bean != null) {
                    scenics.add(bean);
                }
            }
        } catch (JSONException ignore) {

        }
    }
}
