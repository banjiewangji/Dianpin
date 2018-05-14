package com.why.dianpin.scenic.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.Scenic;
import com.why.dianpin.scenic.adapter.ScenicListAdapter;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.util.HttpUtil;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class ScenicListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ScenicListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_scenic_list);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        mRecyclerView = UIUtils.findView(this, R.id.recycler_view);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("必去景点排行");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAdapter = new ScenicListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
//        HttpUtil.create("scenic/getScenicList")
//                .get(new HttpUtil.HttpCallback() {
//                    @Override
//                    public void onSuccess(JSONObject result) {
//                        final JSONArray scenicList = result.optJSONArray("scenics");
//                        final ArrayList<ScenicListBean> beans = new ArrayList<>();
//                        for (int i = 0, len = scenicList.length(); i < len; i++) {
//                            beans.add(ScenicListBean.fromJson(scenicList.optJSONObject(i)));
//                        }
//                        if (mAdapter != null) {
//                            mAdapter.setData(beans);
//                        }
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        Toaster.show(TextUtils.isEmpty(message) ? "获取列表失败" : message);
//                    }
//                });

        final ArrayList<ScenicListBean> beans = new ArrayList<>();
        beans.add(getScenic());
        beans.add(getScenic());
        beans.add(getScenic());
        beans.add(getScenic());
        beans.add(getScenic());

        mAdapter.setData(beans);
    }

    public ScenicListBean getScenic() {
        return new ScenicListBean(3, "故宫博物院"
                , "井壁辉煌风干肉给他告诉她是否收入输入"
                , "https://www.bing.com/s/hpb/NorthMale_EN-US8782628354_1920x1080.jpg"
                , 1, "5A", 4.6, 124, "东城区", "", 50);
    }
}
