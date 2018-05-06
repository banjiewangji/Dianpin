package com.why.dianpin.home;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.why.dianpin.util.UIUtils;

/**
 * @author shidefeng
 * @since 2018/4/10.
 */

public class MainItemDecoration extends RecyclerView.ItemDecoration {

    private final Paint mPaint;
    private final Rect mRect;

    public MainItemDecoration() {
        mPaint = new Paint();
        mPaint.setColor(0xFFEDEDED);
        mRect = new Rect();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            child.getHitRect(mRect);
            c.drawRect(mRect.left, mRect.bottom, mRect.right, mRect.bottom + UIUtils.dp2px(10), mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, UIUtils.dp2px(10), 0, 0);
    }
}
