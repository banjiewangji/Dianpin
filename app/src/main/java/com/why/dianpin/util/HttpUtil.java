package com.why.dianpin.util;

import android.text.TextUtils;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;
import com.why.dianpin.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shidefeng
 * @since 2018/5/3.
 */

public class HttpUtil {


    public static final String HOST = "192.168.1.11";
    public static final String BASE_URL = "http://" + HOST + ":8080/whyServlet/";

    private Novate.Builder mBuilder;

    private String mUrl = "";
    private Map<String, Object> mHeaders = new HashMap<>();
    private Map<String, Object> mParameters = new HashMap<>();

    public static HttpUtil create(String url) {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.mUrl = url;
        httpUtil.mBuilder = new Novate.Builder(MyApplication.getContext())
                .connectTimeout(10)
                .writeTimeout(15)
                .baseUrl(BASE_URL)
                .addCache(false)
                .addLog(false);

        return httpUtil;
    }

    public HttpUtil setHeader(Map<String, Object> headers) {
        mHeaders = headers;
        return this;
    }

    public HttpUtil addHeader(String key, Object header) {
        mHeaders.put(key, header);
        return this;
    }

    public HttpUtil setParameters(Map<String, Object> parameters) {
        mParameters = parameters;
        return this;
    }

    public HttpUtil addParameter(String key, Object paramter) {
        mParameters.put(key, paramter);
        return this;
    }

    public void get(final HttpCallback callback) {
        mBuilder.addHeader(mHeaders);

        Novate novate = mBuilder.build();

        final String getTag = System.currentTimeMillis() + "";

        novate.rxGet(getTag, mUrl, mParameters, new RxStringCallback() {
            @Override
            public void onNext(Object tag, String response) {
                if (!TextUtils.equals(tag.toString(), getTag)) {
                    return;
                }
                if (callback == null) {
                    return;
                }
                if (TextUtils.isEmpty(response)) {
                    callback.onError("result is null");
                }

                int en = 0;
                String em = "";
                JSONObject data = null;
                try {
                    JSONObject json = new JSONObject(response);
                    en = json.optInt("en");
                    em = json.optString("em", "");
                    data = json.optJSONObject("data");
                } catch (JSONException e) {
                    callback.onError("未知错误");
                }
                if (en == 200) {
                    callback.onSuccess(data);
                } else {
                    callback.onError(em);
                }
            }

            @Override
            public void onError(Object tag, Throwable e) {
                if (callback != null) {
                    callback.onError(e == null ? "未知错误" : e.getMessage());
                }
            }

            @Override
            public void onCancel(Object tag, Throwable e) {
                if (callback != null) {
                    callback.onError(e == null ? "未知错误" : e.getMessage());
                }
            }
        });
    }

    public void post(final HttpCallback callback) {
        mBuilder.addHeader(mHeaders);

        Novate novate = mBuilder.build();

        novate.rxPost(mUrl, mParameters, new RxStringCallback() {
            @Override
            public void onNext(Object tag, String response) {
                if (callback == null) {
                    return;
                }
                if (TextUtils.isEmpty(response)) {
                    callback.onError("result is null");
                }

                int en;
                String em;
                JSONObject data;
                try {
                    JSONObject json = new JSONObject(response);
                    en = json.optInt("en");
                    em = json.optString("em", "");
                    data = json.optJSONObject("data");
                } catch (JSONException e) {
                    callback.onError("未知错误");
                    return;
                }
                if (en == 200) {
                    callback.onSuccess(data);
                } else {
                    callback.onError(em);
                }
            }

            @Override
            public void onError(Object tag, Throwable e) {
                if (callback != null) {
                    callback.onError(e == null ? "未知错误" : e.getMessage());
                }
            }

            @Override
            public void onCancel(Object tag, Throwable e) {
                if (callback != null) {
                    callback.onError(e == null ? "未知错误" : e.getMessage());
                }
            }
        });
    }




    public interface HttpCallback {

        void onSuccess(JSONObject result);

        void onError(String message);
    }

}
