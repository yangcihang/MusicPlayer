package com.example.yang.musicplayer.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.base.activity.NoBarActivity;
import com.example.yang.musicplayer.home.activity.MainActivity;

public class SplashActivity extends NoBarActivity{
    /** 跳转到首页 */
    private static final int GO_MAIN = 100;
    private static final int SPLASH_GO_MAIN_DELAY = 1500;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
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

    @Override
    protected void onResume() {
        super.onResume();
        long startTime = System.currentTimeMillis();
        goMainActivity(startTime);
    }

    /**
     * 跳转到MainActivity
     *
     * @param startTime 启动页加载开始时间
     */
    private void goMainActivity(long startTime) {
        long costTime = System.currentTimeMillis() - startTime;
        if (SPLASH_GO_MAIN_DELAY- costTime > 0) {
            handler.sendEmptyMessageDelayed(GO_MAIN, SPLASH_GO_MAIN_DELAY - costTime);
        } else {
            handler.sendEmptyMessage(GO_MAIN);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                    Intent homeIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
            }
        };
}
