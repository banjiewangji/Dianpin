package com.why.dianpin.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;

import com.why.dianpin.MyApplication;

import org.json.JSONObject;

/**
 * @author xiaoyueyue
 * @since 2018/4/10.
 */

public class UIUtils {

    public static Context getContext() {
        return MyApplication.getContext();
    }

    @SuppressWarnings("unchecked")
    public static <V extends View> V findView(Activity activity, @IdRes int id) {
        return (V) activity.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <V extends View> V findView(View view, @IdRes int id) {
        return (V) view.findViewById(id);
    }

    public static int dp2px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }
}
