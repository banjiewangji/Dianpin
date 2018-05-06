package com.why.dianpin.home.beans;

import android.graphics.drawable.Drawable;

/**
 * @author shidefeng
 * @since 2018/4/1.
 */

public class Category {

    public static final int TYPE_SCENIC = 1;
    public static final int TYPE_HOTEL = 2;
    public static final int TYPE_ONE_DAY = 3;
    public static final int TYPE_FOOD = 4;
    public static final int TYPE_PLANE = 5;
    public static final int TYPE_NEARBY = 6;

    public int type;
    public Drawable icon;
    public int bgColor;
    public String title;

    public Category(int type, Drawable icon, int bgColor, String title) {
        this.type = type;
        this.icon = icon;
        this.bgColor = bgColor;
        this.title = title;
    }
}
