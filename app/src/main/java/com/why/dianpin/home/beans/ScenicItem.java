package com.why.dianpin.home.beans;

import java.util.List;

/**
 * @author shidefeng
 * @since 2018/4/1.
 */

public class ScenicItem implements IMainListItem {

    public List<Scenic> scenics;

    @Override
    public int getItemType() {
        return TYPE_SCENIC;
    }
}
