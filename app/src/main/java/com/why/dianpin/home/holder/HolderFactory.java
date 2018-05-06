package com.why.dianpin.home.holder;

import android.view.ViewGroup;

import com.why.dianpin.home.beans.IMainListItem;

/**
 * @author shidefeng
 * @since 2018/4/9.
 */

public class HolderFactory {

    public static MainItemHolder create(ViewGroup parent, int type) {
        switch (type) {
            case IMainListItem.TYPE_CATEGORY:
                return new CategoryItemHolder(parent);
            case IMainListItem.TYPE_SCENIC:
                return new ScenicItemHolder(parent);
            case IMainListItem.TYPE_RECOMMEND:
                return new RecommendItemHolder(parent);
            case IMainListItem.TYPE_TRAVELS:
                return new TravelsItemHolder(parent);
            case IMainListItem.TYPE_MAP:
                return new MapItemHolder(parent);
            default:
                return new DefaultItemHolder(parent);
        }
    }

}
