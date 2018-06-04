package com.why.dianpin.home.holder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.dianpin.home.beans.IMainListItem;
import com.why.dianpin.home.listener.MainItemClickListener;

/**
 * @author xiaoyueyue
 * @since 2018/4/1.
 */

public abstract class MainItemHolder<T extends IMainListItem> extends RecyclerView.ViewHolder {

    Context mContext;
    protected MainItemClickListener mListener;

    MainItemHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        mContext = parent.getContext();
    }

    @SuppressWarnings("unchecked")
    public static <V extends View>V findView(View view, @IdRes int id) {
        return (V)view.findViewById(id);
    }

    public static int dp2px(Context ctx, float dp){
        return (int) (dp * ctx.getResources().getDisplayMetrics().density + 0.5f);
    }

    public void onViewRecycled() {

    }

    public void setMainItemClickListener(MainItemClickListener listener) {
        mListener = listener;
    }

    public abstract void setData(T data);

}
