package com.knoxpo.beatbox.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.knoxpo.beatbox.R;

/**
 * Created by Tejas Sherdiwala on 11/30/2016.
 * &copy; Knoxpo
 */

public abstract class ToolbarActivity extends SingleFragmentActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setSupportActionBar(mToolbar);
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(getToolbarId());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_toolbar;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

}
