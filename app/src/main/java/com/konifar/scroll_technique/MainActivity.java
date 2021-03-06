package com.konifar.scroll_technique;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cookpad.android.issuereporter.ReportMail;
import com.cookpad.android.issuereporter.SystemProfile;
import com.cookpad.android.issuereporter.fragment.IssueReporterFragment;
import com.konifar.scroll_technique.models.pojo.Photo;
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
    private Photo headerPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initIssueReporter();

        headerPhoto = MockUtils.getInstance().getHeaderPhoto();

        initActionBar();
        initListView();

        adapter.addAll(MockUtils.getInstance().getListPhotos());
    }

    private void initIssueReporter() {
        ReportMail reportMail = new ReportMail.Builder()
                .email("yokohamaseichi@gmail.com")
                .subject("Report an issue")
                .body(new SystemProfile(this).toString())
                .build();
        IssueReporterFragment.apply(this, reportMail);
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(headerPhoto.getTitle());
        getSupportActionBar().setTitle(headerPhoto.getTitle());
    }

    private void initListView() {
        header = new PhotoHeaderView(this, headerPhoto);
        mListView.addHeaderView(header);
        adapter = new PhotosArrayAdapter(this);
        mListView.setAdapter(adapter);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // Do nothing
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                header.translate(mToolbar, R.color.theme500);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                headerPhoto = adapter.getItem(position - mListView.getHeaderViewsCount());
                initActionBar();
                header.bindData(headerPhoto);
                header.translate(mToolbar, R.color.theme500);
            }
        });

    }

}
