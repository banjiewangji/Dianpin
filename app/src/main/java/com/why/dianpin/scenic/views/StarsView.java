package com.why.dianpin.scenic.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.why.dianpin.R;

/**
 * @author shidefeng
 * @since 2018/5/7.
 */

public class StarsView extends LinearLayout {

    private ImageView mStar1;
    private ImageView mStar2;
    private ImageView mStar3;
    private ImageView mStar4;
    private ImageView mStar5;

    public StarsView(Context context) {
        super(context);
        init(context);
    }

    public StarsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StarsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.stars_layout, this, true);
        mStar1 = (ImageView) findViewById(R.id.star1);
        mStar2 = (ImageView) findViewById(R.id.star2);
        mStar3 = (ImageView) findViewById(R.id.star3);
        mStar4 = (ImageView) findViewById(R.id.star4);
        mStar5 = (ImageView) findViewById(R.id.star5);
    }

    private int getDrawableId(double grade, double min, double max) {
        return grade <= min ? R.drawable.vector_star_border : (grade < max ? R.drawable.vector_star_half : R.drawable.vector_star_full);
    }

    public void update(double grade) {
        mStar1.setImageResource(getDrawableId(grade, 0.0, 1.0));
        mStar2.setImageResource(getDrawableId(grade, 1.0, 2.0));
        mStar3.setImageResource(getDrawableId(grade, 2.0, 3.0));
        mStar4.setImageResource(getDrawableId(grade, 3.0, 4.0));
        mStar5.setImageResource(getDrawableId(grade, 4.0, 5.0));
    }
}
