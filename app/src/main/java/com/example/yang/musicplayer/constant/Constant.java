package com.example.yang.musicplayer.constant;

/**
 * @author YangCihang
 * @since 17/7/14.
 * email yangcihang@hrsoft.net
 */

public interface Constant {
    String BROADCAST_NAME = "com.example.yang.musicplayer.broadcast";

    int NOTIFICATION_ID = 0x1; //播放器通知的id
    String KEY_FLAG = "flag";
    int FLAG_PAUSE = 0x2;
    int FLAG_NEXT = 0x3;
    int FLAG_PRE = 0x4;
    int FLAG_PLAY = 0x5;
    int FLAG_CANCEL = 0x6;
}
