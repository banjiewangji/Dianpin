package com.why.dianpin.home.holder;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.home.beans.Category;
import com.why.dianpin.home.beans.CategoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shidefeng
 * @since 2018/4/1.
 */

public class CategoryItemHolder extends MainItemHolder<CategoryItem> {

    private GridView mGridView;
    private GridAdapter mGridAdapter;

    CategoryItemHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_category);
        mGridView = findView(itemView, R.id.category_grid);
    }

    @Override
    public void setData(CategoryItem data) {
        if (mGridAdapter == null){
            mGridAdapter = new GridAdapter();
            mGridAdapter.setContext(mContext);
            mGridView.setAdapter(mGridAdapter);
        }
        mGridAdapter.setData(data.categories);
    }

    private class GridAdapter extends BaseAdapter {

        private List<Category> mData = new ArrayList<>();
        private Context mContext;

        private void setContext(Context ctx) {
            mContext = ctx;
        }

        private void setData(List<Category> data) {
            if (mData.size() > 0) {
                mData.clear();
            }
            if (data != null && !data.isEmpty()) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridHolder holder;
            if (convertView == null) {
                holder = new GridHolder();
                // 获得容器
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category_item, parent, false);
                // 初始化组件
                holder.icon = (ImageView) convertView.findViewById(R.id.category_icon);
                holder.title = (TextView) convertView.findViewById(R.id.category_title);
                // 给GridHolder附加一个对象
                convertView.setTag(holder);
            } else {
                // 取得GridHolder附加的对象
                holder = (GridHolder) convertView.getTag();
            }

            Category cate = mData.get(position);
            holder.title.setText(cate.title);

            holder.icon.setImageDrawable(cate.icon);

            GradientDrawable bg = (GradientDrawable) holder.icon.getBackground();
            bg = (GradientDrawable) bg.mutate();
            bg.setColor(cate.bgColor);
            holder.icon.setBackgroundDrawable(bg);

            return convertView;
        }
    }

    private static class GridHolder {
        private TextView title;
        private ImageView icon;
    }
}
