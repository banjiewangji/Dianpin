package com.why.dianpin.home.beans;

import com.why.dianpin.recommend.bean.RecommendListBean;
import com.why.dianpin.travel.bean.TravelBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/9.
 */

public class TravelsItem extends IMainListItem {

    public List<TravelBean> travels;

    @Override
    public int getItemType() {
        return TYPE_TRAVELS;
    }

    @Override
    public void getDataList(JSONArray array) {
        travels = new ArrayList<>();
        if (array == null || array.length() <= 0) {
            return;
        }
        try {
            for (int i = 0; i < array.length(); i++) {
                final JSONObject json = array.getJSONObject(i);
                final TravelBean bean = TravelBean.fromJson(json);
                if (bean != null) {
                    travels.add(bean);
                }
            }
        } catch (JSONException ignore) {

        }
    }
}
