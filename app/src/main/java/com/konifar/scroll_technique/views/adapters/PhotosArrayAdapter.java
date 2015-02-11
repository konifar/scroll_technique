package com.konifar.scroll_technique.views.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.konifar.scroll_technique.R;
import com.konifar.scroll_technique.models.pojo.Photo;
import com.konifar.scroll_technique.utils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PhotosArrayAdapter extends ArrayAdapter<Photo> {

    public PhotosArrayAdapter(Context context) {
        super(context, R.layout.item_photo, new ArrayList<Photo>());
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        bindData(getItem(pos), holder);

        return view;
    }

    private void bindData(Photo photo, ViewHolder holder) {
        ImageLoader.getInstance().displayImage(photo.getImageUrl(), holder.mImgThumb,
                ViewUtils.getInstance().getRoundedImageOptions());
        holder.mTxtTitle.setText(photo.getTitle());
        holder.mTxtSubTitle.setText(photo.getSubTitle());
    }

    public void addAll(List<Photo> photos) {
        if (photos == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            super.addAll(photos);
        } else {
            for (Photo photo : photos) {
                super.add(photo);
            }
        }
    }

    static class ViewHolder {
        @InjectView(R.id.img_thumb)
        ImageView mImgThumb;
        @InjectView(R.id.txt_title)
        TextView mTxtTitle;
        @InjectView(R.id.txt_sub_title)
        TextView mTxtSubTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
