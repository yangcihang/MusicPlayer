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
import com.example.yang.musicplayer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public class BottomPlayer extends RelativeLayout implements Constant{

    @BindView(R.id.img_head_icon)
    ImageView headImg;
    @BindView(R.id.btn_play2)
    ImageButton playBtn;
    @BindView(R.id.btn_pause2)
    ImageButton pauseBtn;
    @BindView(R.id.btn_menu2)
    ImageButton menuBtn;
    @BindView(R.id.btn_playNext2)
    ImageButton nextBtn;
    private Context mcontext;
    private Intent intent;

    public BottomPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        LayoutInflater.from(context).inflate(R.layout.view_bottom_player,this);
        ButterKnife.bind(this);
        intent = new Intent(BROADCAST_NAME);
    }

    @OnClick(R.id.btn_play2)
    void onPlay() {
        intent.putExtra(KEY_FLAG,FLAG_PLAY);
        mcontext.sendBroadcast(intent);
    }

    @OnClick(R.id.btn_pause2)
    void onPause() {
        intent.putExtra(KEY_FLAG,FLAG_PAUSE);
        mcontext.sendBroadcast(intent);
    }

    @OnClick(R.id.btn_playNext2)
    void onNext() {
        intent.putExtra(KEY_FLAG,FLAG_NEXT);
        mcontext.sendBroadcast(intent);
    }

    @OnClick(R.id.img_head_icon)
    void onIconClicked() {
    }

    @OnClick(R.id.btn_menu2)
    void onMenuClicked() {

    }

}
