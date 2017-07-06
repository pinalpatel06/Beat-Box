package com.knoxpo.beatbox.activity;

import android.support.v4.app.Fragment;

import com.knoxpo.beatbox.fragment.MainFragment;

public class MainActivity extends ToolbarActivity {

    @Override
    public Fragment getContentFragment() {
        return new MainFragment().newInstance();
    }
}
