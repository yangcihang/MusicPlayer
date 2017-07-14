package com.example.yang.musicplayer.service;

import android.media.MediaPlayer;

/**
 * 音乐管理
 * @author YangCihang
 * @since 17/7/14.
 * email yangcihang@hrsoft.net
 */

public class MusicController {
    private MediaPlayer mediaPlayer; //播放器
    private String path; //路径
    public MusicController() {
        mediaPlayer = new MediaPlayer();
    }
}

