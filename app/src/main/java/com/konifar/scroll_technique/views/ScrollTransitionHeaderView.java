package com.konifar.scroll_technique.views;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.konifar.scroll_technique.R;
import com.konifar.scroll_technique.models.pojo.Photo;
import com.konifar.scroll_technique.utils.ViewUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class ScrollTransitionHeaderView extends LinearLayout {

    private RectF rect1 = new RectF();
    private RectF rect2 = new RectF();

    public ScrollTransitionHeaderView(Context context) {
        super(context);
    }

    abstract ImageView getImgHeader();

    abstract View getImgHeaderCover();

    abstract TextView getTitleTextView();

    void bindImgHeader(final Photo photo) {
        ImageLoader.getInstance().displayImage(photo.getImageUrl(), getImgHeader());
    }

    public void translate(final Toolbar toolbar, final int colorResId) {
        float top = getTop();
        float transitionHeight = (float) (getImgHeader().getHeight() - toolbar.getHeight());

        // image translation
        ViewHelper.setTranslationY(getImgHeader(), -top * 0.5f);
//        getImgHeader().setTranslationY(-top * 0.5f);

        float ratio = caltulateRatio(-top, transitionHeight);

        // background color
        final ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(colorResId));
        colorDrawable.setAlpha((int) (ratio * 255));
        ViewUtils.getInstance().setBackground(getImgHeaderCover(), colorDrawable);

        // text scale
        interpolate(toolbar, ratio);

        TextView textView = ViewUtils.getInstance().getToolbarTextView(toolbar);
        if (caltulateRatio(-top, transitionHeight) >= 1f) {
            toolbar.setBackgroundResource(colorResId);
            textView.setTextColor(getResources().getColor(R.color.white));
        } else {
            toolbar.setBackgroundResource(android.R.color.transparent);
            textView.setTextColor(getResources().getColor(android.R.color.transparent));
        }
    }

    private float caltulateRatio(float scrollDistance, float transitionHeight) {
        float ratio = scrollDistance / transitionHeight;
        if (ratio > 1f) {
            ratio = 1f;
        } else if (ratio < 0f) {
            ratio = 0f;
        }
        return ratio;
    }

    private void interpolate(Toolbar toolbar, float ratio) {
        float interpolation = ViewUtils.INTERPOLATOR.getInterpolation(ratio);
        TextView toolbarTextView = ViewUtils.getInstance().getToolbarTextView(toolbar);
        getOnScreenRect(rect1, getTitleTextView());
        getOnScreenRect(rect2, toolbarTextView);

        float scaleX = 1.0F + interpolation * (rect2.width() / rect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (rect2.height() / rect1.height() - 1.0F);
        float translationX = 0.5F * (interpolation * (rect2.left + rect2.right - rect1.left - rect1.right));

        ViewHelper.setTranslationX(getTitleTextView(), translationX);
        ViewHelper.setScaleX(getTitleTextView(), scaleX);
        ViewHelper.setScaleY(getTitleTextView(), scaleY);
    }

    private RectF getOnScreenRect(RectF rect, View view) {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

}
