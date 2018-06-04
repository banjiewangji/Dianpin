package com.why.dianpin.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author xiaoyueyue
 * @since 2018/4/19.
 */

public class StatusFrameLayout extends FrameLayout {

    private OnStatusListener mListener;

    public StatusFrameLayout(Context context) {
        super(context);
    }

    public StatusFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mListener != null) {
            mListener.onAttachedToWindow();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mListener != null) {
            mListener.onDetachedFromWindow();
        }
    }

    public void setOnStatusListener(OnStatusListener listener) {
        mListener = listener;
    }

    public interface OnStatusListener {

        void onAttachedToWindow();

        void onDetachedFromWindow();
    }
}
