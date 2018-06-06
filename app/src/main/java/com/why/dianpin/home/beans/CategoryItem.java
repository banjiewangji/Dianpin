package com.why.dianpin.home.beans;

import com.why.dianpin.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyueyue
 * @since 2018/4/1.
 */

public class CategoryItem extends IMainListItem {

    public List<Category> categories;

    @Override
    public int getItemType() {
        return TYPE_CATEGORY;
    }

    @Override
    public void getDataList(JSONArray array) {
        categories = new ArrayList<>();
        categories.add(new Category(Category.TYPE_RECOMMEND, R.drawable.vector_hotel, 0xFFFF3030, "出行推荐"));
        categories.add(new Category(Category.TYPE_SCENIC, R.drawable.vector_scenic, 0xFF33CCFF, "景点"));
        categories.add(new Category(Category.TYPE_MAP, R.drawable.vector_location_white, 0xFFF4A460, "地图"));
        categories.add(new Category(Category.TYPE_TRAVEL, R.drawable.vector_plane, 0xFF7CCD7C, "游记"));
        categories.add(new Category(Category.TYPE_QUESTION, R.drawable.vector_question, 0xFFFFB90F, "问答"));
        categories.add(new Category(Category.TYPE_LIKE, R.drawable.vector_nearby, 0xFF5CACEE, "猜你喜欢"));
    }

}
