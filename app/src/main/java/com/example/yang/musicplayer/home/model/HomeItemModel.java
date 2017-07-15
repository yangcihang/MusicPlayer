package com.example.yang.musicplayer.home.model;

/**
 * @author YangCihang
 * @since 17/7/15.
 * email yangcihang@hrsoft.net
 */

public class HomeItemModel {

    private int titleResources;
    private String title;
    private int songsNum;

    public HomeItemModel(int titleResources, String title, int songsNum) {
        this.titleResources = titleResources;
        this.title = title;
        this.songsNum = songsNum;
    }

    public int getTitleResources() {
        return titleResources;
    }

    public void setTitleResources(int titleResources) {
        this.titleResources = titleResources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSongsNum() {
        return songsNum;
    }

    public void setSongsNum(int songsNum) {
        this.songsNum = songsNum;
    }
}
