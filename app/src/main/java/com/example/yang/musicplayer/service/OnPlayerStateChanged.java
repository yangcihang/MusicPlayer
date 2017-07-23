package com.example.yang.musicplayer.service;

import com.example.yang.musicplayer.base.model.MusicInfo;

/**
 * 音乐状态改变的监听
 * @author YangCihang
 * @since 17/7/15.
 * email yangcihang@hrsoft.net
 */

public interface OnPlayerStateChanged {
    void changePlayerViewState(boolean isPlaying);

    // TODO: 2017/7/17 0017 当切换音乐时，改变状态
    void changeMusicInfoState(int currentPosition, MusicInfo musicInfo);
}
