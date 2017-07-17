package com.example.yang.musicplayer.service;

import com.example.yang.musicplayer.base.model.MusicInfo;

import java.util.List;

/**
 * @author YangCihang.
 * @since 2017/7/17 0017.
 * Email yangcihang@hrsoft.net
 */

public interface MusicControllerInterface {
    void onPlay(int pos);

    void onPause();

    void onLooping(boolean isLooping);

    int getCurrentPosition();

    int getDuration();

    void onStop();

    void onPrePlay();

    void onNext();

    void setMusicListPosition(int position);

    int getMusicListPosition();

    MusicInfo getCurrentMusicInfo();

    void reloadMusicList(List<MusicInfo> musicInfoList);
}
