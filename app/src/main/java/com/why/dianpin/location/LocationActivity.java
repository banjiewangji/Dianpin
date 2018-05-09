package com.why.dianpin.location;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.why.dianpin.util.ToolbarHelper;
import com.why.dianpin.util.view.BaseActivity;
import com.why.dianpin.R;

/**
 * @author shidefeng
 * @since 2018/4/18.
 */

public class LocationActivity extends BaseActivity implements AMap.OnMapClickListener, LocationSource, AMapLocationListener {

    private MapView mMapView = null;
    private TextView mLocationResult = null;
    private AMap mAMap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_location);

        mMapView = (MapView) findViewById(R.id.map);
        mLocationResult = (TextView) findViewById(R.id.location_result);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        ToolbarHelper toolbarHelper = new ToolbarHelper((Toolbar) findViewById(R.id.tool_bar));
        toolbarHelper.setTitle("地图");
        toolbarHelper.setBackgroundColorRes(R.color.colorPrimary);
        toolbarHelper.setNavigation(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.getUiSettings().setRotateGesturesEnabled(false);
            mAMap.moveCamera(CameraUpdateFactory.zoomBy(6));
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        mAMap.setOnMapClickListener(this);
        mAMap.setLocationSource(this);// 设置定位监听
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(
                BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        mAMap.setMyLocationStyle(myLocationStyle);
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapClick(LatLng latLng) {
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(this);
            // 设置定位监听
            mLocationClient.setLocationListener(this);

            AMapLocationClientOption option = new AMapLocationClientOption();
            // 设置为高精度定位模式
            option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 只是为了获取当前位置，所以设置为单次定位
            option.setOnceLocation(true);
            // 设置定位参数
            mLocationClient.setLocationOption(option);
            mLocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            StringBuilder sb = new StringBuilder();
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if (aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                sb.append("定位成功" + "\n");
                sb.append("定位类型: ").append(aMapLocation.getLocationType()).append("\n");
                sb.append("经    度    : ").append(aMapLocation.getLongitude()).append("\n");
                sb.append("纬    度    : ").append(aMapLocation.getLatitude()).append("\n");
                sb.append("精    度    : ").append(aMapLocation.getAccuracy()).append("米").append("\n");
                sb.append("提供者    : ").append(aMapLocation.getProvider()).append("\n");

                sb.append("速    度    : ").append(aMapLocation.getSpeed()).append("米/秒").append("\n");
                sb.append("角    度    : ").append(aMapLocation.getBearing()).append("\n");
                // 获取当前提供定位服务的卫星个数
                sb.append("星    数    : ").append(aMapLocation.getSatellites()).append("\n");
                sb.append("国    家    : ").append(aMapLocation.getCountry()).append("\n");
                sb.append("省            : ").append(aMapLocation.getProvince()).append("\n");
                sb.append("市            : ").append(aMapLocation.getCity()).append("\n");
                sb.append("城市编码 : ").append(aMapLocation.getCityCode()).append("\n");
                sb.append("区            : ").append(aMapLocation.getDistrict()).append("\n");
                sb.append("区域 码   : ").append(aMapLocation.getAdCode()).append("\n");
                sb.append("地    址    : ").append(aMapLocation.getAddress()).append("\n");
                sb.append("兴趣点    : ").append(aMapLocation.getPoiName()).append("\n");
                mLocationResult.setText(sb.toString());
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": "
                        + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }
}
