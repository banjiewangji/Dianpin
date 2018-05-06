package com.why.dianpin.util;

import android.view.Gravity;
import android.widget.Toast;

import com.why.dianpin.MyApplication;

/**
 * @author shidefeng
 * @since 2018/5/6.
 */

public class Toaster {

    public static void show(String text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(String text, int duration) {
        Toast toast = Toast.makeText(MyApplication.getContext(), text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
