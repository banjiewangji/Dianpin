package com.why.dianpin.home.beans;

import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/1.
 */

public class CategoryItem implements IMainListItem {

    public List<Category> categories;

    @Override
    public int getItemType() {
        return TYPE_CATEGORY;
    }

}
