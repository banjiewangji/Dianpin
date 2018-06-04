package com.why.dianpin.util;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * @author xiaoyueyue
 * @since 2018/5/6.
 */

public class ToolbarHelper {

    private Toolbar mToolbar;

    public ToolbarHelper(Toolbar toolbar) {
        mToolbar = toolbar;
        mToolbar.setTitleTextColor(0xFFFFFFFF);

    }

    public void asActionBar(AppCompatActivity activity) {
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setBackgroundColorRes(@ColorRes int colorId) {
        setBackgroundColor(mToolbar.getResources().getColor(colorId));
    }

    public void setBackgroundColor(@ColorInt int color) {
        mToolbar.setBackgroundColor(color);
    }

    public void setNavigation(@DrawableRes int iconId, View.OnClickListener listener) {
        if (iconId != 0) {
            mToolbar.setNavigationIcon(iconId);
        }
        mToolbar.setNavigationOnClickListener(listener);
    }

    public void setOverflowIcon(@DrawableRes int id) {
        mToolbar.setOverflowIcon(mToolbar.getResources().getDrawable(id));
    }

    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    public void setTitleColor(@ColorInt int color) {
        mToolbar.setTitleTextColor(color);
    }

    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
        mToolbar.setOnMenuItemClickListener(listener);
    }

    /**
     * 在Toolbar右边增加菜单按钮,按钮的点击事件在 {@link Toolbar.OnMenuItemClickListener} 中处理
     *
     * @param itemId
     * @param itemText
     * @param iconResId 按钮图标
     */
    public MenuItem addRightMenu(int itemId, CharSequence itemText, @DrawableRes int iconResId) {
        if (mToolbar != null) {
            Menu menu = mToolbar.getMenu();
            itemId = itemId == 0 ? Menu.NONE : itemId;
            MenuItem item = menu.add(Menu.NONE, itemId, Menu.NONE, itemText);
            if (iconResId > 0) {
                item.setIcon(iconResId);
            }
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            return item;
        }
        return null;
    }
}
