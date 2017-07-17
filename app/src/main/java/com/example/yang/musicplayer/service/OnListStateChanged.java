package com.example.yang.musicplayer.service;

/**
 * @author YangCihang.
 * @since 2017/7/17 0017.
 * Email yangcihang@hrsoft.net
 */

interface OnListStateChanged {
    // TODO: 2017/7/17 0017 实现列表的状态改变，controller中实现,binder和controller同时定义，最后在main中实例化监听。
    void onListStateChanged(int position);
}
