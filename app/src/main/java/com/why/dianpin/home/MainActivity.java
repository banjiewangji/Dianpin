package com.why.dianpin.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.Category;
import com.why.dianpin.home.beans.CategoryItem;
import com.why.dianpin.home.beans.IMainListItem;
import com.why.dianpin.home.beans.MapItem;
import com.why.dianpin.home.beans.Recommend;
import com.why.dianpin.home.beans.RecommendItem;
import com.why.dianpin.home.beans.Scenic;
import com.why.dianpin.home.beans.ScenicItem;
import com.why.dianpin.home.beans.Travels;
import com.why.dianpin.home.beans.TravelsItem;
import com.why.dianpin.user.views.UserLoginActivity;
import com.why.dianpin.user.views.UserRegisterActivity;
import com.why.dianpin.util.LoginHelper;
import com.why.dianpin.util.PreferenceUtil;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.view.BaseActivity;

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
                        startActivity(new Intent(MainActivity.this, UserRegisterActivity.class));
                        break;
                    case R.id.profile:

                        break;
                    case R.id.quit:
                        PreferenceUtil.setValue(PreferenceUtil.KEY_USERNAME, "");
                        updateMenu();
                        break;
                }
                return false;
            }
        });
    }

    private void initData() {
        ArrayList<IMainListItem> items = new ArrayList<>();

        items.add(getCategoryItem());
        items.add(getRecommendItem());
        items.add(getScenicItem());
        items.add(getTravelsItem());
        items.add(getMapItem());

        mListAdapter.setData(items);

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

    private CategoryItem getCategoryItem() {
        CategoryItem cateItem = new CategoryItem();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(Category.TYPE_RECOMMEND, getIcon(R.drawable.vector_hotel), 0xFFFF3030, "出行推荐"));
        categories.add(new Category(Category.TYPE_SCENIC, getIcon(R.drawable.vector_scenic), 0xFF33CCFF, "景点"));
        categories.add(new Category(Category.TYPE_MAP, getIcon(R.drawable.vector_location_white), 0xFFF4A460, "地图"));
        categories.add(new Category(Category.TYPE_TRAVEL, getIcon(R.drawable.vector_plane), 0xFF7CCD7C, "游记"));
        categories.add(new Category(Category.TYPE_QUESTION, getIcon(R.drawable.vector_question), 0xFFFFB90F, "问答"));
        categories.add(new Category(Category.TYPE_LIKE, getIcon(R.drawable.vector_nearby), 0xFF5CACEE, "猜你喜欢"));
        cateItem.categories = categories;
        return cateItem;
    }

    private ScenicItem getScenicItem() {
        ScenicItem item = new ScenicItem();
        ArrayList<Scenic> scenics = new ArrayList<>();
        scenics.add(new Scenic(R.drawable.scenic_gugong, "故宮博物院"));
        scenics.add(new Scenic(R.drawable.scenic_houhai, "后海"));
        scenics.add(new Scenic(R.drawable.scenic_wangfujing, "王府井"));
        scenics.add(new Scenic(R.drawable.scenic_tiantan, "天坛"));
        scenics.add(new Scenic(R.drawable.scenic_gongwangfu, "恭王府"));
        scenics.add(new Scenic(R.drawable.scenic_bowuguan, "博物馆"));
        scenics.add(new Scenic(R.drawable.scenic_maozhuxi, "毛主席纪念馆"));
        item.scenics = scenics;
        return item;
    }

    private TravelsItem getTravelsItem() {
        TravelsItem item = new TravelsItem();
        ArrayList<Travels> travels = new ArrayList<>();
        travels.add(new Travels(1, R.drawable.scenic_gugong, "故宮博分公司故宮博分公司让他发送给物院故宮博分公司让他发送给物院让他发送给物院", "2015年2月出游 小两口 行程4天"));
        travels.add(new Travels(1, R.drawable.scenic_gugong, "故宮博分公司让他发送给物院", "2014年2月出游 和朋友 行程6天"));
        travels.add(new Travels(1, R.drawable.scenic_gugong, "公司让他发送给物院", "2017年8月出游 家族出游 行程2天"));
        item.travels = travels;
        return item;
    }

    private RecommendItem getRecommendItem() {
        RecommendItem item = new RecommendItem();
        ArrayList<Recommend> travels = new ArrayList<>();
        travels.add(new Recommend("防坑宝典"));
        travels.add(new Recommend("北京必体验"));
//        travels.add(new Recommend("最佳旅行时间"));
//        travels.add(new Recommend("北京当地特产"));
        travels.add(new Recommend("旅行路线推荐"));
        travels.add(new Recommend("北京特色美食"));
        item.recommends = travels;
        return item;
    }

    private MapItem getMapItem() {
        MapItem mapItem = new MapItem();
        return mapItem;
    }

    private Drawable getIcon(int id) {
        return VectorDrawableCompat.create(getResources(), id, getTheme());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        updateMenu();
        return true;
    }
}
