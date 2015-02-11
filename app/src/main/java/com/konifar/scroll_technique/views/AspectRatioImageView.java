package com.konifar.scroll_technique.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.konifar.scroll_technique.R;

public class AspectRatioImageView extends ImageView {

    private static final float DEFAULT_RATIO = 1.618f;

    private float ratio;
    private boolean autoScale;

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView);
        try {
            ratio = a.getFloat(R.styleable.AspectRatioImageView_imageRatio, DEFAULT_RATIO);
            autoScale = a.getBoolean(R.styleable.AspectRatioImageView_autoScale, false);
        } finally {
            a.recycle();
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (autoScale) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int height = (int) Math.ceil((float) width
                        * (float) drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
                setMeasuredDimension(width, height);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }

        } else {
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            double propotionalHeight = parentWidth / ratio;

            if (propotionalHeight < getSuggestedMinimumHeight()) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            } else {
                setMeasuredDimension(parentWidth, (int) propotionalHeight);
            }
        }
    }

}
