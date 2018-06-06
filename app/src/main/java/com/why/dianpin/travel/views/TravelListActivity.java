package com.why.dianpin.travel.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.why.dianpin.R;
import com.why.dianpin.scenic.bean.ScenicDetailHeaderBean;
import com.why.dianpin.scenic.bean.ScenicDetailItemBean;
import com.why.dianpin.scenic.bean.ScenicListBean;
import com.why.dianpin.travel.adapter.TravelListAdapter;
import com.why.dianpin.travel.bean.IDetailBean;
import com.why.dianpin.travel.bean.TravelBean;
import com.why.dianpin.util.HttpUtils;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class TravelListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TravelListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_travel_list);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        mRecyclerView = UIUtils.findView(this, R.id.recycler_view);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("游记");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
//        toolbarHelper.addRightMenu(R.id.menu_travel_publish_id, "", R.drawable.ic_add);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        toolbarHelper.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == R.id.menu_travel_publish_id) {
//                    Toaster.show("网络连接有误");
//                }
//                return false;
//            }
//        });

        mAdapter = new TravelListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        HttpUtils.doPost("travel/getTravelList", null, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                final JSONArray travelList = result.optJSONArray("travels");
                final ArrayList<TravelBean> beans = new ArrayList<>();
                for (int i = 0, len = travelList.length(); i < len; i++) {
                    beans.add(TravelBean.fromJson(travelList.optJSONObject(i)));
                }
                if (mAdapter != null) {
                    mAdapter.setData(beans);
                }
            }

            @Override
            public void onError(String message) {
                Toaster.show(TextUtils.isEmpty(message) ? "获取列表失败" : message);
            }
        });

//        final ArrayList<TravelBean> beans = new ArrayList<>();
//        beans.add(getScenic());
//        beans.add(getScenic());
//        beans.add(getScenic());
//        beans.add(getScenic());
//        beans.add(getScenic());
//
//        mAdapter.setData(beans);
    }

    public TravelBean getScenic() {
        return new TravelBean(1, "精华", "傅红雪", "故宫博物院"
                , "https://www.bing.com/s/hpb/NorthMale_EN-US8782628354_1920x1080.jpg"
                , 453, 344555, 124, 4, "东城区");
    }
}
