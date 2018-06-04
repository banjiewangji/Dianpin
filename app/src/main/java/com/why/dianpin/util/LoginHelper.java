package com.why.dianpin.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.why.dianpin.user.views.UserLoginActivity;

/**
 * @author xiaoyueyue
 * @since 2018/5/6.
 */

public class LoginHelper {

    public static boolean isLogin() {
        String username = PreferenceUtil.getValue(PreferenceUtil.KEY_USERNAME, "");
        return !TextUtils.isEmpty(username);
    }

    public static void showLoginDialog(final Activity activity) {
        showLoginDialog(activity, new LoginHelper.OnDialogClickListener() {
            @Override
            public void onCancel() {
            }

            @Override
            public void onConfirm() {
                activity.startActivity(new Intent(activity, UserLoginActivity.class));
            }
        });
    }

    public static void showLoginDialog(Context context, final OnDialogClickListener listener) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage("您还未登录，请先登录")
                .setTitle(" ")
                .setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onCancel();
                        }
                    }
                })
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onConfirm();
                        }
                    }
                })
                .create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public interface OnDialogClickListener {

        void onCancel();

        void onConfirm();
    }
}
