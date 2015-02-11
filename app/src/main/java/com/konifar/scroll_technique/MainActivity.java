package com.konifar.scroll_technique;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.konifar.scroll_technique.utils.MockUtils;
import com.konifar.scroll_technique.views.PhotoHeaderView;
import com.konifar.scroll_technique.views.adapters.PhotosArrayAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.list_view)
    ListView mListView;

    private PhotosArrayAdapter adapter;
    private PhotoHeaderView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        initListView();

        adapter.addAll(MockUtils.getInstance().getListPhotos());
    }

    private void initListView() {
        header = new PhotoHeaderView(this, MockUtils.getInstance().getHeaderPhoto());
        mListView.addHeaderView(header);
        adapter = new PhotosArrayAdapter(this);
        mListView.setAdapter(adapter);
    }

}
