package com.why.dianpin.home.beans;

import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.recommend.bean.RecommendListBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/14.
 */

public class QuestionItem extends IMainListItem {

    public List<QuestionBean> questions;

    @Override
    public int getItemType() {
        return TYPE_QUESTION;
    }

    @Override
    public void getDataList(JSONArray array) {
        questions = new ArrayList<>();
        if (array == null || array.length() <= 0) {
            return;
        }
        try {
            for (int i = 0; i < array.length(); i++) {
                final JSONObject json = array.getJSONObject(i);
                final QuestionBean bean = QuestionBean.fromJson(json);
                if (bean != null) {
                    questions.add(bean);
                }
            }
        } catch (JSONException ignore) {

        }

    }
}
