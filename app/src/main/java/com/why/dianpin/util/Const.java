package com.why.dianpin.util;

import android.graphics.Color;

public class Const {
	/**
	 * 地图中绘制多边形、圆形的边界颜色
	 * @since 3.3.0
	 */
	public static final int STROKE_COLOR = Color.argb(180, 63, 145, 252);
	/**
	 * 地图中绘制多边形、圆形的填充颜色
	 * @since 3.3.0
	 */
	public static final int FILL_COLOR = Color.argb(163, 118, 212, 243);

	/**
	 * 地图中绘制多边形、圆形的边框宽度
	 * @since 3.3.0
	 */
	public static final float STROKE_WIDTH = 5F;

	/**
	 * 修改此处
	 */
	public static final String HOST = "172.16.139.84";
	public static final String BASE_URL = "http://" + HOST + ":8080/whyServlet/";
}