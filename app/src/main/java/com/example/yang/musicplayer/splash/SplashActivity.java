package com.example.yang.musicplayer.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.yang.musicplayer.MusicPlayerApplication;
import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.base.activity.NoBarActivity;
import com.example.yang.musicplayer.home.activity.MainActivity;
import com.example.yang.musicplayer.service.MusicPlayerService;
import com.example.yang.musicplayer.utils.ToastUtil;
import com.example.yang.musicplayer.utils.permission.PermissionsUtils;

import io.reactivex.functions.Consumer;

public class SplashActivity extends NoBarActivity {
    /**
     * 跳转到首页
     */
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
        new PermissionsUtils(this).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    MusicPlayerApplication.getInstance().initMusicController();
                    long startTime = System.currentTimeMillis();
                    goMainActivity(startTime);
                } else {
                    ToastUtil.showToast("无法读取本地音乐资源");
                    MusicPlayerApplication.getInstance().exitApp();
                }
            }
        });
    }

    /**
     * 跳转到MainActivity
     *
     * @param startTime 启动页加载开始时间
     */
    private void goMainActivity(long startTime) {
        long costTime = System.currentTimeMillis() - startTime;
        if (SPLASH_GO_MAIN_DELAY - costTime > 0) {
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
