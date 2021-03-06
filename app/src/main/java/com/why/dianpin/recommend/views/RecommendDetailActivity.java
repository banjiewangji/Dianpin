package com.why.dianpin.recommend.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.why.dianpin.R;
import com.why.dianpin.recommend.adapter.RecommendDetailAdapter;
import com.why.dianpin.recommend.bean.RecommendDetailHeaderBean;
import com.why.dianpin.recommend.bean.RecommendDetailItemBean;
import com.why.dianpin.recommend.bean.RecommendListBean;
import com.why.dianpin.travel.bean.IDetailBean;
import com.why.dianpin.util.HttpUtils;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.UIUtils;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class RecommendDetailActivity extends BaseActivity {

    public static final String DETAIL_ID = "detail_id";

    private RecyclerView mRecyclerView;
    private RecommendDetailAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_recommend_detail);

        initViews();
        initEvent();
        initData();
//        refreshData();
    }

    private void initViews() {
        mRecyclerView = UIUtils.findView(this, R.id.recycler_view);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("出行推荐详情");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAdapter = new RecommendDetailAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("recommendId", getIntent().getIntExtra(DETAIL_ID, 0) + "");
        HttpUtils.doPost("recommend/getRecommendDetail", params, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                final JSONObject recommendJson = result.optJSONObject("recommend");
                final RecommendListBean recommendDetail = RecommendListBean.fromJson(recommendJson);
                final RecommendDetailHeaderBean headerBean = new RecommendDetailHeaderBean();
                RecommendListBean.copy(headerBean, recommendDetail);

                List<IDetailBean> data = new ArrayList<>();
                data.add(headerBean);
                final String detail = recommendDetail.detail;
                data.add(new RecommendDetailItemBean(IDetailBean.TYPE_ITEM_TEXT, detail.replace("\\n", "\n")));

                if (mAdapter != null) {
                    mAdapter.setData(data);
                }
            }

            @Override
            public void onError(String message) {
                Toaster.show(TextUtils.isEmpty(message) ? "获取失败" : message);
            }
        });
    }


//    public RecommendListBean getTravelBean() {
//
//        final RecommendListBean bean = new RecommendListBean(1, "旅行路线推荐", "我要从南走到北，我还要从北走到黑", "https://b1-q.mafengwo.net/s7/M00/E0/0A/wKgB6lT3Bt6ALlZyAAVYJMJaMKM21.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90", 432, "");
//        bean.detail = "目的地：北京" +
//                "     只能说某人的都市情节怎么都消退不了，我还是偏爱自然风光多一些。但北京很" +
//                "特别，因为我同时也更偏爱历史小说多一些，那里有个很大的四合院，曾经住过许多奇" +
//                "葩的皇帝小儿、皇帝老儿。我一直把它定义为一座有趣的城市。于是在这个农历羊年第" +
//                "一天，我们喜气洋洋地出发啦~ \n\n        景山公园--恭俭胡同--什刹海--烟袋斜街--鼓楼--南锣鼓巷\n\n        " +
//                "$$https://n2-q.mafengwo.net/s7/M00/DD/42/wKgB6lT3BWyAa7IMAAcykmIbfhM05.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90$$" +
//                "        算是小远门，动用了专属大红箱。杭州地铁2号线，空荡荡、簇簇新。整节车" +
//                "厢都被我们承包了，有一种塘主般的莫名骄傲！$$https://b2-q.mafengwo.net/s7/" +
//                "M00/DD/4F/wKgB6lT3BXeAKOoRAAejgND55Lc59.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90$$" +
//                "女汉子就是这样一手提箱，一手拿物，身上还背着个包。不用震惊啊，任你是貌美如花的萝莉、御姐、" +
//                "茶婊，老公是摄影师就得默默接受这样的待遇，尤其这个摄影师还喜欢街拍。脑中循环的是《回娘家》" +
//                "，左手一只鸡，右手一只鸭，身上还背着个胖娃娃，哟哟切克闹，大家一起唱起来！高铁五小时，路上" +
//                "\n\n        看了好几集《奇葩说》，简直是消磨时间的大好利器，然后我想说马东实在是太萌了好吗。" +
//                "$$https://b1-q.mafengwo.net/s7/M00/E0/0A/wKgB6lT3Bt6ALlZyAAVYJMJaMKM21.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90$$" +
//                "嗯哼，我得儿意的笑~酒店在建国门附近，二环内五星酒店三晚加往返机票，每人1999元，虽然最后因为" +
//                "春节机票实在是紧张，去程变成了高铁，但其实高铁更方便。马云爸爸的双十一活动就是给力！烦请淘宝" +
//                "旅游（阿里旅游）看到以上软文联系我，钱拿来，不用谢。（好多蜂友问酒店的问题，是北京万豪酒店，" +
//                "单订的话会比较贵）\n\n        下午三点，从酒店出发。帽子、手套、羽绒衣、防风裤、雪地靴，一个" +
//                "都不能少。说好的春节蓝呢？迎接我们的是标志性的雾霾天，好失落。";
//        return bean;
//    }
//
//    public void refreshData() {
//        final RecommendListBean recommendDetail = getTravelBean();
//        final RecommendDetailHeaderBean headerBean = new RecommendDetailHeaderBean();
//        RecommendListBean.copy(headerBean, recommendDetail);
//
//        List<IDetailBean> data = new ArrayList<>();
//        data.add(headerBean);
//        final String detail = recommendDetail.detail;
//        data.add(new RecommendDetailItemBean(IDetailBean.TYPE_ITEM_TEXT, detail));
//
//        if (mAdapter != null) {
//            mAdapter.setData(data);
//        }
//    }
}
