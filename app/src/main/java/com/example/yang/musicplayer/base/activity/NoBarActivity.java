package com.example.yang.musicplayer.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yang.musicplayer.base.activity.BaseActivity;

/**
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public abstract class NoBarActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }
}
