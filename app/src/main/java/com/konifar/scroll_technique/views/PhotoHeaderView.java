package com.konifar.scroll_technique.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.konifar.scroll_technique.R;
import com.konifar.scroll_technique.models.pojo.Photo;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PhotoHeaderView extends ScrollTransitionHeaderView {

    @InjectView(R.id.dummy_img_header)
    View mDummyImgHeader;
    @InjectView(R.id.img_header)
    AspectRatioImageView mImgHeader;
    @InjectView(R.id.txt_title)
    TextView mTxtTitle;

    public PhotoHeaderView(Context context, Photo photo) {
        super(context);
        inflate(context, R.layout.ui_photo_header, this);
        ButterKnife.inject(this);
        bindData(photo);
    }

    public void bindData(final Photo photo) {
        bindImgHeader(photo);

        mTxtTitle.setText(photo.getTitle());
    }

    @Override
    ImageView getImgHeader() {
        return mImgHeader;
    }

    @Override
    View getImgHeaderCover() {
        return mDummyImgHeader;
    }

    @Override
    TextView getTitleTextView() {
        return mTxtTitle;
    }

}
