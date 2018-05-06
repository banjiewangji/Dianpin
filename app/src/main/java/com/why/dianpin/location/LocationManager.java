package com.why.dianpin.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * @author shidefeng
 * @since 2018/4/12.
 */

public class LocationManager implements AMapLocationListener {

    private final Context mContext;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private OnLocationListener mListener;


    public LocationManager(Context context) {
        mContext = context;

    }

    public void startLocation() {//初始化定位
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);

        AMapLocationClientOption option = new AMapLocationClientOption();
        // 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        // 设置为高精度定位模式
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 只是为了获取当前位置，所以设置为单次定位
        option.setOnceLocation(true);
        // 设置定位参数
        mLocationClient.setLocationOption(option);

        if (mLocationClient != null) {
            mLocationClient.startLocation();
        }
    }

    public void stopLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
    }

    public void destroy() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
    }

    public void setOnLocationListener(OnLocationListener listener) {
        mListener = listener;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation == null) {
            return;
        }
        if (aMapLocation.getErrorCode() == 0) {
            if (mListener != null) {
                mListener.onLocation(aMapLocation.getAddress());
            }
        } else {
            String errText = "定位失败," + aMapLocation.getErrorCode() + ": "
                    + aMapLocation.getErrorInfo();
            if (mListener != null) {
                mListener.onLocation(errText);
            }
        }
    }

    public interface OnLocationListener {
        void onLocation(String location);
    }
}
