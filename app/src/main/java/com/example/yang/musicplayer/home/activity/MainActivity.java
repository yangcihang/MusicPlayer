package com.example.yang.musicplayer.home.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.TextView;

import com.example.yang.musicplayer.MusicPlayerApplication;
import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.base.activity.NoBarActivity;
import com.example.yang.musicplayer.base.model.MusicInfo;
import com.example.yang.musicplayer.home.fragment.HomeFragment;
import com.example.yang.musicplayer.service.MusicPlayerService;
import com.example.yang.musicplayer.service.OnPlayerStateChanged;
import com.example.yang.musicplayer.utils.ToastUtil;
import com.example.yang.musicplayer.widget.MainBottomPlayer;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends NoBarActivity {

    private static final long duringTime = 2000; //点击时间间隔
    private long firstPressBackTime = -1;//第一次点击退出的时间，默认为-1
    private HomeFragment homeFragment;
    private MusicPlayerService.MusicBinder musicBinder;

    @BindView(R.id.txt_exit_app)
    TextView exitTxt;
    @BindView(R.id.layout_bottom)
    MainBottomPlayer bottomLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {
    }

    @Override
    protected void initView() {
        homeFragment = new HomeFragment();
        addFragment(R.id.frame_home, homeFragment, null);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(new Intent(this, MusicPlayerService.class), connection, BIND_AUTO_CREATE); //绑定服务
    }

    @OnClick(R.id.txt_exit_app)
    void onExit() {
        MusicPlayerApplication.getInstance().exitApp();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicBinder = (MusicPlayerService.MusicBinder) iBinder;
            bottomLayout.setMusicBinder(musicBinder);
            musicBinder.setPlayerStateChangedListener(new OnPlayerStateChanged() {
                @Override
                public void clickedChangePlayerState(boolean isPlaying) {
                    bottomLayout.onStateChanged(isPlaying);
                }

                @Override
                public void switchChangeViewState(int currentPosition, MusicInfo musicInfo) {
                    bottomLayout.refreshView(musicInfo);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            ToastUtil.showToast("服务连接断开");
        }
    };

    /**
     * 主页重写返回键
     */
    @Override
    public void onBackPressed() {
        if (homeFragment.isVisible()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - firstPressBackTime < duringTime) {
                finish();
            } else {
                firstPressBackTime = currentTime;
                ToastUtil.showToast("再点击一次退出");
            }


        } else {
            super.onBackPressed();
        }
    }
}
