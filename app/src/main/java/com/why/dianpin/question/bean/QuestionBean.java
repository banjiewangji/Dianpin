package com.why.dianpin.question.bean;

import com.why.dianpin.recommend.bean.RecommendListBean;
import com.why.dianpin.user.bean.UserBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/14.
 */

public class QuestionBean {

    public int id;
    public String question;
    public long timestamp;
    public List<AnswerBean> answers;

    public UserBean author;

    public static QuestionBean fromJson(JSONObject json) {
        final QuestionBean bean = new QuestionBean();
        if (json == null) {
            return bean;
        }
        bean.id = json.optInt("id");
        bean.question = json.optString("question");
        bean.timestamp = json.optLong("timestamp");

        JSONArray answers = json.optJSONArray("answers");
        if (answers != null && answers.length() > 0) {
            ArrayList<AnswerBean> answerBeans = new ArrayList<>();
            for (int i = 0, len = answers.length(); i < len; i++) {
                answerBeans.add(AnswerBean.fromJson(answers.optJSONObject(i)));
            }
            bean.answers = answerBeans;
        }
        bean.author = UserBean.fromJson(json.optJSONObject("author"));
        return bean;
    }

}
