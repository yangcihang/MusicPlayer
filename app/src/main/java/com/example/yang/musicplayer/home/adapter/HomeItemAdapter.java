package com.example.yang.musicplayer.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.base.adapter.BaseCommAdapter;
import com.example.yang.musicplayer.home.model.HomeItemModel;

/**
 * @author YangCihang
 * @since 17/7/15.
 * email yangcihang@hrsoft.net
 */

public class HomeItemAdapter extends BaseCommAdapter<HomeItemModel> {

    public HomeItemAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HomeItemModel model = dataList.get(i);
        view = inflater.inflate(R.layout.view_item_home, viewGroup, false);
        ImageView titleImg = view.findViewById(R.id.img_title);
        TextView titleTxt = view.findViewById(R.id.txt_title);
        TextView numTxt = view.findViewById(R.id.txt_num);
        titleImg.setImageResource(model.getTitleResources());
        titleTxt.setText(model.getTitle());
        // TODO: 17/7/15 歌曲数量
        return view;
    }
}
