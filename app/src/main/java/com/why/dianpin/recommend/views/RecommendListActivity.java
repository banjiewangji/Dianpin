package com.why.dianpin.recommend.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.why.dianpin.R;
import com.why.dianpin.recommend.adapter.RecommendListAdapter;
import com.why.dianpin.recommend.bean.RecommendListBean;
import com.why.dianpin.scenic.adapter.ScenicListAdapter;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.util.HttpUtil;
import com.why.dianpin.util.HttpUtils;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class RecommendListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecommendListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_recommend_list);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        mRecyclerView = UIUtils.findView(this, R.id.recycler_view);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("出行推荐");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAdapter = new RecommendListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
//        HashMap<String, String> params = new HashMap<>();
//        params.put("pageNum", "3");
//        HttpUtils.doPost("recommend/getRecommendList", params, new HttpUtils.HttpCallback() {
//            @Override
//            public void onSuccess(JSONObject result) {
//                final JSONArray scenicList = result.optJSONArray("scenicList");
//                final ArrayList<ScenicListBean> beans = new ArrayList<>();
//                for (int i = 0, len = scenicList.length(); i < len; i++) {
//                    beans.add(ScenicListBean.fromJson(scenicList.optJSONObject(i)));
//                }
//            }
//
//            @Override
//            public void onError(String message) {
//                Toaster.show(TextUtils.isEmpty(message) ? "获取失败" : message);
//            }
//        });
//        HttpUtil.create("recommend/getRecommendList")
//                .addParameter("pageNum", 3)
//                .get(new HttpUtil.HttpCallback() {
//                    @Override
//                    public void onSuccess(JSONObject result) {
//                        final JSONArray scenicList = result.optJSONArray("scenicList");
//                        final ArrayList<ScenicListBean> beans = new ArrayList<>();
//                        for (int i = 0, len = scenicList.length(); i < len; i++) {
//                            beans.add(ScenicListBean.fromJson(scenicList.optJSONObject(i)));
//                        }
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        Toaster.show(TextUtils.isEmpty(message) ? "登录失败" : message);
//                    }
//                });
//
        final ArrayList<RecommendListBean> beans = new ArrayList<>();
        beans.add(getScenic());
        beans.add(getScenic());
        beans.add(getScenic());
        beans.add(getScenic());
        beans.add(getScenic());

        mAdapter.setData(beans);
    }

    public RecommendListBean getScenic() {
        return new RecommendListBean(3
                , "故宫博物馆"
                , "黄琉璃瓦顶、青白实底座，饰以金壁辉煌的油画"
                , "https://www.bing.com/s/hpb/NorthMale_EN-US8782628354_1920x1080.jpg"
                , 323424, "");
    }
}
