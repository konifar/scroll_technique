package com.konifar.scroll_technique.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.konifar.scroll_technique.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.lang.reflect.Field;

public class ViewUtils {

    public static final Interpolator INTERPOLATOR = new DecelerateInterpolator();
    private static final String TAG = ViewUtils.class.getSimpleName();
    private static final int IMAGE_FADE_DURATION_MILLS = 300;
    private static ViewUtils instance;
    private DisplayImageOptions defaultImageOptions;
    private DisplayImageOptions roundedImageOptions;

    private ViewUtils() {
        //
    }

    public static ViewUtils getInstance() {
        if (instance == null) {
            instance = new ViewUtils();
        }
        return instance;
    }

    public DisplayImageOptions getDefaultImageOptions() {
        if (defaultImageOptions == null) {
            defaultImageOptions = new DisplayImageOptions.Builder()
                    .cacheOnDisc(true)
                    .cacheInMemory(true)
                    .showImageForEmptyUri(R.color.grey200)
                    .showImageOnLoading(R.color.grey200)
                    .showImageOnFail(R.color.grey200)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .displayer(new FadeInBitmapDisplayer(IMAGE_FADE_DURATION_MILLS))
                    .build();
        }
        return defaultImageOptions;
    }

    public DisplayImageOptions getRoundedImageOptions() {
        if (roundedImageOptions == null) {
            roundedImageOptions = new DisplayImageOptions.Builder()
                    .cacheOnDisc(true)
                    .cacheInMemory(true)
                    .showImageForEmptyUri(R.color.grey200)
                    .showImageOnLoading(R.color.grey200)
                    .showImageOnFail(R.color.grey200)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .displayer(new RoundedBitmapDisplayer(100))
                    .build();
        }
        return roundedImageOptions;
    }

    public TextView getToolbarTextView(final Toolbar toolbar) {
        TextView titleTextView = null;
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(toolbar);
        } catch (NoSuchFieldException e) {
            Log.e(TAG, e.toString());
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.toString());
        }
        return titleTextView;
    }

    public void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }


}
