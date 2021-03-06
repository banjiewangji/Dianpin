package com.why.dianpin.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.why.dianpin.MyApplication;

/**
 * @author xiaoyueyue
 * @since 2018/5/1.
 */

public class PreferenceUtil {

    public static final String KEY_USER = "key_user";

    public static void setValue(String key, String username) {
        SharedPreferences mSharedPreferences = MyApplication.getContext().getSharedPreferences("why_Preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, username);
        editor.apply();
    }

    public static String getValue(String key, String defaultValue) {
        SharedPreferences mSharedPreferences = MyApplication.getContext().getSharedPreferences("why_Preference", Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, defaultValue);
    }
}
