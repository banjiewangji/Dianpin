package com.why.dianpin.home.beans;

import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/9.
 */

public class RecommendItem implements IMainListItem {

    public List<Recommend> recommends;

    @Override
    public int getItemType() {
        return TYPE_RECOMMEND;
    }
}
