package com.example.yang.musicplayer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.example.yang.musicplayer.MusicPlayerApplication;
import com.example.yang.musicplayer.R;
import com.example.yang.musicplayer.constant.Constant;
import com.example.yang.musicplayer.home.activity.MainActivity;
import com.example.yang.musicplayer.utils.ToastUtil;

import static android.app.Notification.FLAG_ONGOING_EVENT;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * 音乐播放的service
 * @author YangCihang
 * @since 17/7/12.
 * email yangcihang@hrsoft.net
 */

public class MusicPlayerService extends Service implements Constant{

    private String path; //路径
    private MusicBroadcast musicBroadcast; //广播接收器
    private MusicBinder musicBinder;
    private RemoteViews remoteView; //加载notification的view
    private NotificationManager notificationManager; //通知栏管理
    private boolean isNotificaton = false;
    private MusicController musicController;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicController = new MusicController();
        initBroadcast();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 初始化广播
     */
    private void initBroadcast() {
        musicBroadcast = new MusicBroadcast();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_NAME);
        registerReceiver(musicBroadcast,intentFilter);
    }

    /**
     * 初始化通知
     */
    private void initNotification() {
        remoteView = new RemoteViews(this.getPackageName(), R.layout.view_music_player_notification);
        Intent intent = new Intent(BROADCAST_NAME);
        intent.putExtra(KEY_FLAG,FLAG_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this,FLAG_NEXT,intent,FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.img_next,nextPendingIntent);
        intent.putExtra(KEY_FLAG,FLAG_PRE);
        PendingIntent prePendingIntent = PendingIntent.getBroadcast(this,FLAG_PRE,intent, FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.img_previous,prePendingIntent);
        intent.putExtra(KEY_FLAG,FLAG_PAUSE);
        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(this,FLAG_PAUSE,intent,FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.img_pause,pausePendingIntent);
        intent.putExtra(KEY_FLAG,FLAG_PLAY);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this,FLAG_PLAY,intent,FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.img_play,playPendingIntent);
        intent.putExtra(KEY_FLAG,FLAG_CANCEL);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(this,FLAG_CANCEL,intent,FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.img_cancel,cancelPendingIntent);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        setNotificationRemoteView();
    }

    /**
     * 设置Notification的View
     */
    public void setNotificationRemoteView() {
        remoteView.setTextViewText(R.id.txt_title,"这是title");
        remoteView.setTextViewText(R.id.txt_author,"这是author");
        remoteView.setImageViewResource(R.id.img_head,R.drawable.img_album_background);
        Notification notification = new Notification();
        // TODO: 17/7/14 应该到播放音乐的地方，而不是主页，暂时先这么写
        Intent intentToMain = new Intent(MusicPlayerApplication.getInstance(), MainActivity.class);
        notification.contentIntent = PendingIntent.getActivity(this,0,intentToMain,0);
        notification.contentView = remoteView;
        notification.icon = R.drawable.icon;
        notification.tickerText = "123";
        notification.flags |= FLAG_ONGOING_EVENT; //设置不能被点击删除
        startForeground(NOTIFICATION_ID,notification);
    }

    /**
     * 取消通知
     */
    private void cancelNotification() {
        // TODO: 17/7/14 取消通知，停止音乐
        stopForeground(true);
        notificationManager.cancel(NOTIFICATION_ID);
        isNotificaton = false;
    }

    /**
     * 音乐接收器
     */
    private class MusicBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isNotificaton) {
                isNotificaton = true;
                initNotification();
            }
            int flag = intent.getIntExtra(KEY_FLAG,0);
            switch (flag) {
                case FLAG_PLAY:
                    ToastUtil.showToast("点击了播放");
                    break;
                case FLAG_NEXT:
                    ToastUtil.showToast("点击了下一首");
                    break;
                case FLAG_CANCEL:
                    ToastUtil.showToast("点击了取消");
                    cancelNotification();
                    break;
                case FLAG_PRE:
                    ToastUtil.showToast("点击了前一首");
                    break;
                case FLAG_PAUSE:
                    ToastUtil.showToast("点击了暂停");
                    break;
                default:
                    ToastUtil.showToast(R.string.toast_logical_error);
                    break;
            }

        }
    }

    private class MusicBinder extends Binder {

    }
}
