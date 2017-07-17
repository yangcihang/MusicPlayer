package com.example.yang.musicplayer.service;

/**
 * 音乐状态改变的监听
 * @author YangCihang
 * @since 17/7/15.
 * email yangcihang@hrsoft.net
 */

public interface OnPlayerStateChanged {
    void changePlayerState(boolean isPlaying);
}
