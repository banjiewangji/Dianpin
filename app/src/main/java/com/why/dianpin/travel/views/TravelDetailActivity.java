package com.why.dianpin.travel.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.why.dianpin.R;
import com.why.dianpin.travel.adapter.TravelDetailAdapter;
import com.why.dianpin.travel.adapter.TravelListAdapter;
import com.why.dianpin.travel.bean.IDetailBean;
import com.why.dianpin.travel.bean.TravelBean;
import com.why.dianpin.travel.bean.TravelDetailHeaderBean;
import com.why.dianpin.travel.bean.TravelDetailItemBean;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import java.util.ArrayList;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class TravelDetailActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TravelDetailAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_travel_detail);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        mRecyclerView = UIUtils.findView(this, R.id.recycler_view);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("游记详情");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAdapter = new TravelDetailAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
//        HttpUtil.create("scenic/getScenicList")
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

        final ArrayList<IDetailBean> beans = new ArrayList<>();
        beans.add(getScenic(IDetailBean.TYPE_HEADER));
        beans.add(getScenic(IDetailBean.TYPE_ITEM_TEXT));
        beans.add(getScenic(IDetailBean.TYPE_ITEM_IMAGE));
        beans.add(getScenic(IDetailBean.TYPE_ITEM_TEXT));
        beans.add(getScenic(IDetailBean.TYPE_ITEM_IMAGE));
        beans.add(getScenic(IDetailBean.TYPE_ITEM_TEXT));
        beans.add(getScenic(IDetailBean.TYPE_ITEM_IMAGE));
        beans.add(getScenic(IDetailBean.TYPE_ITEM_TEXT));

        mAdapter.setData(beans);
    }

    public IDetailBean getScenic(int type) {
        if (type == IDetailBean.TYPE_HEADER) {
            return new TravelDetailHeaderBean(1, "精华", "傅红雪", "北京的冬天太冷，我没有足够的衣裳拍照--春节北京四日游"
                    , "https://www.bing.com/s/hpb/NorthMale_EN-US8782628354_1920x1080.jpg"
                    , 453, 344555, 124, 4, "朋友");
        } else if (type == IDetailBean.TYPE_ITEM_TEXT) {
            return new TravelDetailItemBean(IDetailBean.TYPE_ITEM_TEXT, "        只能说某人的都市情节怎么都消退不了，我还是偏爱自然风光多一些。但北京很特别，因为我同时也更偏爱历史小说多一些，那里有个很大的四合院，\n        曾经住过许多奇葩的皇帝小儿、皇帝老儿。我一直把它定义为一座有趣的城市。于是在这个农历羊年第一天，我们喜气洋洋地出发啦~");
        } else if (type == IDetailBean.TYPE_ITEM_IMAGE) {
            return new TravelDetailItemBean(IDetailBean.TYPE_ITEM_IMAGE, "https://www.bing.com/s/hpb/NorthMale_EN-US8782628354_1920x1080.jpg");
        }
        return null;
    }
}
