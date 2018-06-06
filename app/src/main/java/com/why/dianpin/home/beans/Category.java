package com.why.dianpin.home.beans;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * @author xiaoyueyue
 * @since 2018/4/1.
 */

public class Category {

    public static final int TYPE_SCENIC = 1;
    public static final int TYPE_RECOMMEND = 2;
    public static final int TYPE_QUESTION = 3;
    public static final int TYPE_MAP = 4;
    public static final int TYPE_TRAVEL = 5;
    public static final int TYPE_LIKE = 6;

    public int type;
    public int icon;
    public int bgColor;
    public String title;

    public Category(int type, @DrawableRes int icon, int bgColor, String title) {
        this.type = type;
        this.icon = icon;
        this.bgColor = bgColor;
        this.title = title;
    }
}
