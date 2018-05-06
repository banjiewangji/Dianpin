package com.why.dianpin.home.holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.IMainListItem;
import com.why.dianpin.location.LocationActivity;
import com.why.dianpin.location.LocationManager;
import com.why.dianpin.util.view.StatusFrameLayout;

/**
 * @author shidefeng
 * @since 2018/4/17.
 */

public class MapItemHolder extends MainItemHolder implements View.OnClickListener {

    private LocationManager mManager;

    private final TextView mHeaderTitle;
    private final TextView mHeaderAllBtn;
    private final StatusFrameLayout mMapContainer;
    private final TextView mTvLocation;

    MapItemHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_list_map);

        mHeaderTitle = findView(itemView, R.id.item_header_title);
        mHeaderAllBtn = findView(itemView, R.id.item_header_all);
        mMapContainer = findView(itemView, R.id.map_container);
        mTvLocation = findView(itemView, R.id.tv_location);

        mManager = new LocationManager(mContext);
        mManager.setOnLocationListener(new LocationManager.OnLocationListener() {
            @Override
            public void onLocation(String location) {
                mTvLocation.setText(location);
            }
        });
    }

    @Override
    public void setData(IMainListItem data) {
        mHeaderTitle.setText("地图");
        mHeaderAllBtn.setText("查看");
        mHeaderAllBtn.setOnClickListener(this);
        mMapContainer.setOnClickListener(this);
        mMapContainer.setOnStatusListener(new StatusFrameLayout.OnStatusListener() {

            @Override
            public void onAttachedToWindow() {
                mManager.startLocation();
            }

            @Override
            public void onDetachedFromWindow() {
                mManager.stopLocation();
            }
        });
    }

    @Override
    public void onViewRecycled() {
        super.onViewRecycled();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_header_all:
            case R.id.map_container:
                mContext.startActivity(new Intent(mContext, LocationActivity.class));
                break;
        }
    }
}
