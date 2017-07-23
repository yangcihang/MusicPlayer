package com.example.yang.musicplayer.service;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.example.yang.musicplayer.MusicPlayerApplication;
import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.base.model.MusicInfo;
import com.example.yang.musicplayer.utils.MusicUtils;
import com.example.yang.musicplayer.utils.ToastUtil;

import java.io.IOException;
import java.util.List;

/**
 * 音乐管理
 *
 * @author YangCihang
 * @since 17/7/14.
 * email yangcihang@hrsoft.net
 */

public class MusicController implements MusicControllerInterface {
    private List<MusicInfo> musicInfoList; //音乐列表
    private MediaPlayer mediaPlayer; //播放器
    private int musicListPosition = 0;//默认为0
    private boolean isPause = false; //pause的flag
    private OnPlayerStateChanged onPlayerStateChangedListener;

    MusicController() {
        musicInfoList = MusicUtils.getAllSongs(MusicPlayerApplication.getInstance());
        ToastUtil.showToast("数据初始化完成");
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                ToastUtil.showToast("播放出现了问题，现在播放下一首");
                onNext();
                return false;
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (!mediaPlayer.isLooping()) {
                    onNext();
                }
            }
        });
    }

    @Override
    public void onPlay(int pos) {
        musicListPosition = pos;
        //切换音乐时候执行
        if (onPlayerStateChangedListener != null && !musicInfoList.isEmpty()) {
            onPlayerStateChangedListener.changeMusicInfoState(pos, musicInfoList.get(pos));
            onPlayerStateChangedListener.changePlayerViewState(true);
        }
        if (isPause) {
            onContinue();
        } else {
            try {
                if (!musicInfoList.isEmpty()) {
                    mediaPlayer.setDataSource(MusicPlayerApplication.getInstance(), Uri.parse(musicInfoList.get(pos).getFileUrl()));
                    mediaPlayer.prepare(); //同步准备
                    mediaPlayer.start();
                } else {
                    ToastUtil.showToast(R.string.toast_cannot_find_local_music);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPause() {
        isPause = true;
        mediaPlayer.pause();
        onPlayerStateChangedListener.changePlayerViewState(false);
    }


    @Override
    public void onNext() {
        onStop();
        if (musicListPosition == musicInfoList.size() - 1) {
            musicListPosition = -1;
        }
        onPlay(musicListPosition += 1);
    }

    @Override
    public void onPrePlay() {
        if (musicListPosition == 0) {
            musicListPosition = musicInfoList.size();
        }
        onStop();
        onPlay(musicListPosition -= 1);
    }

    @Override
    public void onStop() {
        isPause = false;
        mediaPlayer.stop();
        mediaPlayer.reset();
        onPlayerStateChangedListener.changePlayerViewState(false);
        // TODO: 2017/7/17 0017 是否需要释放
    }


    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }


    @Override
    public void onLooping(boolean isLooping) {
        mediaPlayer.setLooping(isLooping);
    }


    @Override
    public int getMusicListPosition() {
        return musicListPosition;
    }

    @Override
    public MusicInfo getCurrentMusicInfo() {
        return musicInfoList.get(musicListPosition);
    }

    @Override
    public void setMusicListPosition(int musicListPosition) {
        this.musicListPosition = musicListPosition;
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    private void onContinue() {
        mediaPlayer.start();
        isPause = false;
    }

    private void onRelease() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }

    @Override
    public void reloadMusicList(List<MusicInfo> musicInfoList) {
        this.musicInfoList = musicInfoList;
        onRelease();
    }

    public void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 状态改变的监听
     */
    public void setOnPlayerStateChangedListener(OnPlayerStateChanged onPlayerStateChangedListener) {
        this.onPlayerStateChangedListener = onPlayerStateChangedListener;
    }
}

