package com.example.yang.musicplayer.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.constant.Constant;
import com.example.yang.musicplayer.service.MusicPlayerService;
import com.example.yang.musicplayer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public class MainBottomPlayer extends RelativeLayout implements Constant {

    @BindView(R.id.img_head_icon)
    ImageView headImg;
    @BindView(R.id.btn_play)
    ImageButton playBtn;
    @BindView(R.id.btn_pause)
    ImageButton pauseBtn;
    @BindView(R.id.btn_menu)
    ImageButton menuBtn;
    @BindView(R.id.btn_playNext)
    ImageButton nextBtn;

    private Context mcontext;
    private Intent intent;
    private MusicPlayerService.MusicBinder musicBinder;
    public MainBottomPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        LayoutInflater.from(context).inflate(R.layout.view_bottom_player, this);
        ButterKnife.bind(this);
        intent = new Intent(BROADCAST_NAME);
    }

    public void setMusicBinder(MusicPlayerService.MusicBinder musicBinder) {
        this.musicBinder = musicBinder;
    }

    @OnClick(R.id.btn_play)
    void onPlay() {
        musicBinder.onPlay(musicBinder.getMusicListPosition());
    }

    @OnClick(R.id.btn_pause)
    void onPause() {
        musicBinder.onPause();
    }

    @OnClick(R.id.btn_playNext)
    void onNext() {
        musicBinder.onNext();
    }

    @OnClick(R.id.img_head_icon)
    void onIconClicked() {

    }

    @OnClick(R.id.btn_menu)
    void onMenuClicked() {

    }

    public void onStateChanged(boolean isPlaying) {
        if (isPlaying) {
            pauseBtn.setVisibility(VISIBLE);
            playBtn.setVisibility(GONE);
        } else {
            pauseBtn.setVisibility(GONE);
            playBtn.setVisibility(VISIBLE);
        }
    }
}
