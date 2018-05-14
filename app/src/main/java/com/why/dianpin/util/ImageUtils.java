package com.why.dianpin.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author shidefeng
 * @since 2018/5/13.
 */

public class ImageUtils {

    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Uri uri = Uri.parse(imageUrl);
        String host = uri.getHost();

        Glide.with(context).load(uri.toString().replace(host, HttpUtil.HOST)).into(imageView);
    }

}
