package com.example.yang.musicplayer.home.activity;
import android.widget.TextView;

import com.example.yang.musicplayer.MusicPlayerApplication;
import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.base.activity.NoBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends NoBarActivity {

    @BindView(R.id.txt_exit_app)
    TextView exitTxt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.txt_exit_app)
    void onExit() {
        MusicPlayerApplication.getInstance().exitApp();
    }
}
