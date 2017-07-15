package com.example.yang.musicplayer.service;

import android.media.MediaPlayer;

import com.example.yang.musicplayer.MusicPlayerApplication;
import com.example.yang.musicplayer.base.model.MusicInfo;
import com.example.yang.musicplayer.utils.MusicUtils;

import java.util.List;

/**
 * 音乐管理
 *
 * @author YangCihang
 * @since 17/7/14.
 * email yangcihang@hrsoft.net
 */

public class MusicController {
    private List<MusicInfo> musicInfoList;
    private MediaPlayer mediaPlayer; //播放器
    private String path; //路径

    public MusicController() {
        mediaPlayer = new MediaPlayer();
        musicInfoList = MusicUtils.getAllSongs(MusicPlayerApplication.getInstance());
    }
}

