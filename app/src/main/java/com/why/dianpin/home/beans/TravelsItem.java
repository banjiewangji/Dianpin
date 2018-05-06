package com.why.dianpin.home.beans;

import java.util.List;

/**
 * @author shidefeng
 * @since 2018/4/9.
 */

public class TravelsItem implements IMainListItem {

    public List<Travels> travels;

    @Override
    public int getItemType() {
        return TYPE_TRAVELS;
    }
}
