package com.example.yang.musicplayer.home.fragment;

import android.widget.GridView;

import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.base.fragment.BaseFragment;
import com.example.yang.musicplayer.home.adapter.HomeItemAdapter;
import com.example.yang.musicplayer.home.model.HomeItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author YangCihang
 * @since 17/7/15.
 * email yangcihang@hrsoft.net
 */

public class HomeFragment extends BaseFragment {

    private int songsNum = 100;
    private HomeItemAdapter adapter;
    private List<HomeItemModel> dataList;
    @BindView(R.id.grid_home)
    GridView homeGrid;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable() {
    }

    @Override
    protected void initView() {
        adapter = new HomeItemAdapter(getActivity());
        homeGrid.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        dataList = new ArrayList<>();
        initData();
    }

    private void initData() {
        // TODO: 17/7/15 数量需要动态改变
        dataList.add(new HomeItemModel(R.drawable.icon_local_music, "我的音乐", songsNum));
        dataList.add(new HomeItemModel(R.drawable.icon_favorites, "我的最爱", 0));
        dataList.add(new HomeItemModel(R.drawable.icon_folder_plus, "文件夹", 0));
        dataList.add(new HomeItemModel(R.drawable.icon_artist_plus, "歌手", 0));
        dataList.add(new HomeItemModel(R.drawable.icon_album_plus, "专辑", 0));
        adapter.setData(dataList);
    }
}
