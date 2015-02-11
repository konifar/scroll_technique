package com.konifar.scroll_technique;

import android.app.Application;

import com.konifar.scroll_technique.utils.ViewUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initUniversalImageLoader();
    }

    private void initUniversalImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .discCache(new UnlimitedDiscCache(StorageUtils.getCacheDirectory(getApplicationContext())))
                .discCacheSize(64 * 1024 * 1024)
                .memoryCache(new LruMemoryCache(16 * 1024 * 1024))
                .memoryCacheSize(16 * 1024 * 1024)
                .defaultDisplayImageOptions(ViewUtils.getInstance().getDefaultImageOptions())
                .build();

        ImageLoader.getInstance().init(config);
    }

}
