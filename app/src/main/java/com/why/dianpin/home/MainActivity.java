package com.why.dianpin.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.Category;
import com.why.dianpin.home.beans.CategoryItem;
import com.why.dianpin.home.beans.IMainListItem;
import com.why.dianpin.home.beans.MapItem;
import com.why.dianpin.home.beans.QuestionItem;
import com.why.dianpin.home.beans.Recommend;
import com.why.dianpin.home.beans.RecommendItem;
import com.why.dianpin.home.beans.Scenic;
import com.why.dianpin.home.beans.ScenicItem;
import com.why.dianpin.home.beans.Travels;
import com.why.dianpin.home.beans.TravelsItem;
import com.why.dianpin.question.bean.AnswerBean;
import com.why.dianpin.question.bean.QuestionBean;
import com.why.dianpin.user.views.UserInfoActivity;
import com.why.dianpin.user.views.UserLoginActivity;
import com.why.dianpin.user.views.UserRegisterActivity;
import com.why.dianpin.util.HttpUtils;
import com.why.dianpin.util.LoginHelper;
import com.why.dianpin.util.PreferenceUtil;
import com.why.dianpin.util.Toaster;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author 王华玥
 * @since 2018/3/31.
 */

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private MainListAdapter mListAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvent();
        initData();

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);

        mListAdapter = new MainListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new MainItemDecoration());
        mRecyclerView.setAdapter(mListAdapter);
    }

    private void initEvent() {
        ToolbarHelper toolbarHelper = new ToolbarHelper(mToolbar);
        toolbarHelper.asActionBar(this);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbarHelper.setOverflowIcon(R.drawable.vector_more);
        toolbarHelper.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.login:
                        startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
                        break;
                    case R.id.register:
                        startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this, UserRegisterActivity.class));
                        break;
                    case R.id.quit:
                        PreferenceUtil.setValue(PreferenceUtil.KEY_USER, "");
                        updateMenu();
                        break;
                }
                return false;
            }
        });
    }

    private void initData() {
        HttpUtils.doPost("servlet/mainList", null, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                final JSONArray mainList = result.optJSONArray("mainList");
                final ArrayList<IMainListItem> beans = new ArrayList<>();
                for (int i = 0, len = mainList.length(); i < len; i++) {
                    beans.add(IMainListItem.fromJson(mainList.optJSONObject(i)));
                }
                if (mListAdapter != null) {
                    mListAdapter.setData(beans);
                }
            }

            @Override
            public void onError(String message) {
                Toaster.show(TextUtils.isEmpty(message) ? "获取列表失败" : message);
            }
        });

        checkLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMenu();
    }

    private void checkLogin() {
        if (!LoginHelper.isLogin()) {
            LoginHelper.showLoginDialog(this);
        }
    }

    private void updateMenu() {
        Menu menu = mToolbar.getMenu();
        MenuItem loginMenu = menu.findItem(R.id.login);
        MenuItem registerMenu = menu.findItem(R.id.register);
        MenuItem profileMenu = menu.findItem(R.id.profile);
        MenuItem quitMenu = menu.findItem(R.id.quit);

        boolean isLogin = LoginHelper.isLogin();

        if (loginMenu != null) {
            loginMenu.setVisible(!isLogin);
        }
        if (registerMenu != null) {
            registerMenu.setVisible(!isLogin);
        }
        if (profileMenu != null) {
            profileMenu.setVisible(isLogin);
        }
        if (quitMenu != null) {
            quitMenu.setVisible(isLogin);
        }
    }



//    private ScenicItem getScenicItem() {
//        ScenicItem item = new ScenicItem();
//        ArrayList<Scenic> scenics = new ArrayList<>();
//        scenics.add(new Scenic(R.drawable.scenic_gugong, "故宮博物院"));
//        scenics.add(new Scenic(R.drawable.scenic_houhai, "后海"));
//        scenics.add(new Scenic(R.drawable.scenic_wangfujing, "王府井"));
//        scenics.add(new Scenic(R.drawable.scenic_tiantan, "天坛"));
//        scenics.add(new Scenic(R.drawable.scenic_gongwangfu, "恭王府"));
//        scenics.add(new Scenic(R.drawable.scenic_bowuguan, "博物馆"));
//        scenics.add(new Scenic(R.drawable.scenic_maozhuxi, "毛主席纪念馆"));
//        item.scenics = scenics;
//        return item;
//    }

//    private TravelsItem getTravelsItem() {
//        TravelsItem item = new TravelsItem();
//        ArrayList<Travels> travels = new ArrayList<>();
//        travels.add(new Travels(1, R.drawable.scenic_gugong, "故宮博分公司故宮博分公司让他发送给物院故宮博分公司让他发送给物院让他发送给物院", "2015年2月出游 小两口 行程4天"));
//        travels.add(new Travels(1, R.drawable.scenic_gugong, "故宮博分公司让他发送给物院", "2014年2月出游 和朋友 行程6天"));
//        travels.add(new Travels(1, R.drawable.scenic_gugong, "公司让他发送给物院", "2017年8月出游 家族出游 行程2天"));
//        item.travels = travels;
//        return item;
//    }
//
//    private RecommendItem getRecommendItem() {
//        RecommendItem item = new RecommendItem();
//        ArrayList<Recommend> travels = new ArrayList<>();
//        travels.add(new Recommend("防坑宝典"));
//        travels.add(new Recommend("北京必体验"));
////        travels.add(new Recommend("最佳旅行时间"));
////        travels.add(new Recommend("北京当地特产"));
//        travels.add(new Recommend("旅行路线推荐"));
//        travels.add(new Recommend("北京特色美食"));
//        item.recommends = travels;
//        return item;
//    }

//    private MapItem getMapItem() {
//        MapItem mapItem = new MapItem();
//        return mapItem;
//    }

//    private QuestionItem getQuestionItem() {
//        final QuestionItem item = new QuestionItem();
//
//        item.questions = new ArrayList<>();
//
//        item.questions.add(getQuestionBean());
//        item.questions.add(getQuestionBean());
//        item.questions.add(getQuestionBean());
//
//        return item;
//
//    }

//    @NonNull
//    private QuestionBean getQuestionBean() {
//        final QuestionBean question = new QuestionBean();
//        question.timestamp = System.currentTimeMillis();
//        question.question = "北京菜什么味道，南方人吃得惯吗";
//        question.answers = new ArrayList<>();
//        question.answers.add(new AnswerBean(0, "还行吧", System.currentTimeMillis(), null));
//        question.answers.add(new AnswerBean(1, "哈哈哈，一点也不好吃", System.currentTimeMillis(), null));
//        return question;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        updateMenu();
        return true;
    }
}
