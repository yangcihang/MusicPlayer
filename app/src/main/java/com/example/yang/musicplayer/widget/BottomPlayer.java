package com.example.yang.musicplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.yang.musicplayer.R;

import butterknife.ButterKnife;

/**
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public class BottomPlayer extends RelativeLayout{
    public BottomPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_bottom_player,this);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
    }
}
