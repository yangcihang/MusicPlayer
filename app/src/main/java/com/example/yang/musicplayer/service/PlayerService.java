package com.example.yang.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public class PlayerService extends Service {
    private final String TAG = "com.example.yang.musicplayer.service.PLAYER_SERVICE";
    private MediaPlayer mediaPlayer = new MediaPlayer(); //播放器对象
    private String path; //路径
    private boolean isPause; //暂停状态
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
