package com.why.dianpin.scenic.views;

import android.content.Context;
import android.content.SharedPreferences;

import com.why.dianpin.MyApplication;

/**
 * @author shidefeng
 * @since 2018/4/30.
 */

public class UserBean {

    public UserBean() {

        SharedPreferences mSharedPreferences = MyApplication.getContext().getSharedPreferences("loginUser",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("user_id", true);
        editor.putString("user_mobile","13811111111");
        editor.apply();

    }
}
